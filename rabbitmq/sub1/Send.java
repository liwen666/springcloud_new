package rabbitmq.sub1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
  private final static String QUEUE_NAME = "hello";
  public static void main(String[] argv) throws Exception {
	ConnectionFactory factory = new ConnectionFactory();// new 一个连接管理的工厂  
    factory.setHost("localhost");//通过工厂设置RabbitMQ服务所在的主机名称或者ip。工厂还负责设置协议版本和认证等工作
    Connection connection = factory.newConnection();// 创建一个到服务器的连接，这里会连接到socket
    Channel channel = connection.createChannel();//创建一个通道，大部分完成任务的API都在通道类里
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);/*声明一个队列，消息将发送到该队列中
    * 声明的队列是幂等的（幂等是指这个操作可以重复多次，并且每次都会得到相同的结果），只有当它不存在的时候才会被创建*/
    String message = "Hello World!";
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));//执行基本的发布。发布消息，消息是字节数组，因此可以是任何数据
    System.out.println(" [x] Sent '" + message + "'");
    channel.close();//关闭通道
    connection.close();//关闭连接
  }
}