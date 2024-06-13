package org.example.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.util.ConnectionUtil;

public class Producer {
    public static void main(String[] args) throws Exception {

        Connection connection= ConnectionUtil.getConnection();


        Channel channel = connection.createChannel();


        String exchangeName = "text_topic";

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true, false, false, null);

        String queue1Name = "test_topic_queue1";

        String queue2Name = "test_topic_queue2";

        channel.queueDeclare(queue1Name, true, false, false, null);
        channel.queueDeclare(queue2Name, true, false, false, null);

        channel.queueBind(queue1Name, exchangeName, "#.error");
        channel.queueBind(queue1Name, exchangeName, "order.*");
        channel.queueBind(queue2Name, exchangeName, "*.*");

        String body = "[所在系统.order][日志级别： info][日志内容：订单生成， 保存成功]";

        channel.basicPublish(exchangeName,"order.info", null, body.getBytes());



        channel.close();
        connection.close();

    }
}
