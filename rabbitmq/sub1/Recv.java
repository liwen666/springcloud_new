package rabbitmq.sub1;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Recv {

//  private final static String QUEUE_NAME = "hello";
	  private static final String QUEUE_NAME = "task_queue";

  public static void main(String[] argv) throws Exception {
	//与生产者相同，我们打开一个连接和一个通道，并声明我们要消费的队列。注意：队列名称需要与生产者的的匹配
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost"); 
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
//    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    /*请注意，我们也在这里声明队列。
    * 因为我们可能会在发布商之前启动消费者，所以我们要确保队列存在，然后再尝试从中消费消息。*/
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    /*运行到这里，我们即将告诉服务器可以将队列中的消息传递给消费者。由于它会异步地推送消息给消费者，所以我们要提供一个对象形式的回调，
     * 该对象将缓冲消息，直到我们准备好使用它们。下面就是一个DefaultConsumer子类*/
   final Consumer consumer = new DefaultConsumer(channel) {//DefaultConsumer是一个实现Consumer接口的类，用于缓冲由服务器推送给我们的消息。
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);/*基本的消费功能。
        * 用于接收队列推送过来的消息，并将消息交给给定的回调类处理 */
  }
}
