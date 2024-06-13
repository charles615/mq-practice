package org.example.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    public static final String HOST_ADDRESS = "172.20.10.11";

    public static Connection getConnection() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("localhost");

        connectionFactory.setPort(5672);

        connectionFactory.setVirtualHost("/");

        connectionFactory.setUsername("guest");

        connectionFactory.setPassword("123456");

        return connectionFactory.newConnection();
    }

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        System.out.println(connection);
        connection.close();
    }

}
