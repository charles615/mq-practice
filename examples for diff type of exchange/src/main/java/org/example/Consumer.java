package org.example;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("172.20.10.11");

        connectionFactory.setPort(5672);

        connectionFactory.setVirtualHost("/");

        connectionFactory.setUsername("guest");

        connectionFactory.setPassword("123456");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

//        channel.queueDeclare("sample_queue", true, false, false,null);

        DefaultConsumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("Body: " + new String(body));
            }
        };

        channel.basicConsume("sample_queue1", true, consumer);





    }
}
