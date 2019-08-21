package rabbitmq.sub6;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class RPCClient {

  private Connection connection;
  private Channel channel;
  private String requestQueueName = "rpc_queue";
  private String replyQueueName;

  public RPCClient() throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");

    connection = factory.newConnection();
    channel = connection.createChannel();

    replyQueueName = channel.queueDeclare().getQueue();//声明一个回调队列
  }

  public String call(String message) throws IOException, InterruptedException {
    String corrId = UUID.randomUUID().toString();//生产一个UUID，作为correlationId(关联id)

    // 创建一个附加属性对象
    AMQP.BasicProperties props = new AMQP.BasicProperties
            .Builder()
            .correlationId(corrId)//设置correlationId
            .replyTo(replyQueueName)//设置回调队列
            .build();

    channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

    // 该阻塞队列的作用是阻塞主线程，将使用response.take()来造成阻塞
    final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

    channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
    /**
     * 处理服务端返回的响应
     */
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        if (properties.getCorrelationId().equals(corrId)) {  // 使用 关联id 验证收到的响应是否是我们所需要的，如果不是则丢弃
          response.offer(new String(body, "UTF-8"));//将收到的合法响应数据，存入阻塞队列中，这样，将会结束 response.take() 的阻塞
        }
      }
    });

    return response.take();//返回响应的数据，如果没有，则阻塞
  }

  public void close() throws IOException {
    connection.close();
  }

  public static void main(String[] argv) {
    RPCClient fibonacciRpc = null;
    String response = null;
    try {
      fibonacciRpc = new RPCClient();

      System.out.println(" [x] Requesting fib(30)");
      response = fibonacciRpc.call("30");
      System.out.println(" [.] Got '" + response + "'");
      System.out.println("time : "+System.currentTimeMillis());
    }
    catch  (IOException | TimeoutException | InterruptedException e) {
      e.printStackTrace();
    }
    finally {
      if (fibonacciRpc!= null) {
        try {
          fibonacciRpc.close();
        }
        catch (IOException _ignore) {}
      }
    }
  }
}




/*

客户端代码详解：
	我们建立一个连接和通道，并声明一个只有当前客户端使用的队列作为回调队列。
	我们订阅“回调”队列，以便我们可以接收RPC响应。
	我们的调用方法使得实际的RPC请求。
	在这里，我们首先生成一个唯一的correlationId并保存它 ，我们在DefaultConsumer 中实现handleDelivery将使用此值来捕获适当的响应。
	接下来，我们发布请求消息，其中包含两个属性：  replyTo和correlationId。

	发布后，程序需要阻塞，等待适当的响应返回，
	由于我们的消费者交付处理发生在一个单独的线程中，我们将需要一些东西在响应到达之前暂停主线程，	使用BlockingQueue是解决的方案之一。
	这里我们正在创建ArrayBlockingQueue ，容量设置为1，因为我们只需要等待一个响应。

	该handleDelivery方法是做一个很简单的工作，对每一位消费响应消息它会检查的correlationID 是我们需要的响应数据。
	如果是，它将它存入BlockingQueue，同时主线程从BlockingQueue中取出正在等待的响应，
	最后，我们将响应返回给用户。
*/