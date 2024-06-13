package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("172.20.10.11");

        connectionFactory.setPort(5672);

        connectionFactory.setVirtualHost("/");

        connectionFactory.setUsername("guest");

        connectionFactory.setPassword("123456");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("sample_queue1",true, false, false, null);

        String message = "Hi, Charles.";

        channel.basicPublish("", "sample_queue1", null, message.getBytes());

        System.out.println("Sent message: " + message);

        channel.close();
        connection.close();

    }
}
