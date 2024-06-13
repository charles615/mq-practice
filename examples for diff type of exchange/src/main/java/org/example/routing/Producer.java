package org.example.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.util.ConnectionUtil;

public class Producer {
    public static void main(String[] args) throws Exception {

        Connection connection= ConnectionUtil.getConnection();


        Channel channel = connection.createChannel();


        String exchangeName = "text_direct";

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, false, null);

        String queue1Name = "test_direct_queue1";

        String queue2Name = "test_direct_queue2";

        channel.queueDeclare(queue1Name, true, false, false, null);
        channel.queueDeclare(queue2Name, true, false, false, null);

        channel.queueBind(queue1Name, exchangeName, "error");


        channel.queueBind(queue2Name, exchangeName, "info");
        channel.queueBind(queue2Name, exchangeName, "error");
        channel.queueBind(queue2Name, exchangeName, "warning");

        String message = "Log info: Charles used the Delete method, log level Warning.";

        channel.basicPublish(exchangeName,"warning", null, message.getBytes());
        System.out.println(message);

        channel.close();
        connection.close();

    }
}
