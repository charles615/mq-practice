package org.example.topic;

import com.rabbitmq.client.*;
import org.example.util.ConnectionUtil;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws Exception {


        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        String queue2Name = "test_topic_queue2";

        channel.queueDeclare(queue2Name, true, false, false, null);

        Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("Body: " + new String(body));
                System.out.println("queue 2 消费者2 将日志打印到控制台。。。。。。");
            }
        };


        channel.basicConsume(queue2Name,true,consumer);





    }
}
