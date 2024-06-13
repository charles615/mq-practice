package org.example.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.example.util.ConnectionUtil;

public class Producer {
    public static void main(String[] args) throws Exception {

        Connection connection= ConnectionUtil.getConnection();


        Channel channel = connection.createChannel();


        String exchangeName = "text_fanout";

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT, true, false, false, null);

        String queue1Name = "test_fanout_queue1";

        String queue2Name = "test_fanout_queue2";

        channel.queueDeclare(queue1Name, true, false, false, null);
        channel.queueDeclare(queue2Name, true, false, false, null);

        channel.queueBind(queue1Name, exchangeName, "");
        channel.queueBind(queue2Name, exchangeName, "");

        String body = "Log info......... Write by Charles.";

        channel.basicPublish(exchangeName,"", null, body.getBytes());

        channel.close();
        connection.close();

    }
}
