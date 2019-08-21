package rabbitmq.sub2;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Worker {

  private static final String TASK_QUEUE_NAME = "task_queue";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    final Connection connection = factory.newConnection();
    final Channel channel = connection.createChannel();

//    channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    channel.basicQos(1);//一次只接受一封Unack-ed消息（见下文）。-- 设置成其他值好像也没用

    final Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");

        System.out.println(" [x] Received '" + message + "'");
        try {
          doWork(message);
        } finally {
          System.out.println(" [x] Done");
          channel.basicAck(envelope.getDeliveryTag(), false);
        }
      }
    };
    boolean autoAck = false;
    // false--开启消息回执功能。当任务处理完成的时候，会发送回执信息给RabbitMQ，告诉RabbitMQ任务已经完成，可以将此任务消息从队列删除；
    //true -- 关闭消息回执功能。当RabbitMQ将消息发出时，就会将消息从队列中删除；
    channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);

    /**** 当忘记执行 channel.basicAck() 的时候：
      ·	当 autoAck=false 当前工作进程会保留通道的联通，可以继续接收和处理下一个消息；
      · 	当 autoAck=true  当前工作进程在执行完第一个任务的时候后，可能会发送一个回执，
      	并且会关闭通道，不再接收新的消息，若在一次接受了多个任务消息，正常情况下任务
      	会执行，单在发送回执的时候会因为已经关闭了通道，会抛出com.rabbitmq.client.AlreadyClosedException，
      	不过该异常是被RabbitMQ捕获过的，不会造成程序终止 **/
  }

  private static void doWork(String task) {
    for (char ch : task.toCharArray()) {
      if (ch == '.') {
        try {
          Thread.sleep(1);
          System.out.println("任务执行了..");
        } catch (InterruptedException _ignored) {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}

