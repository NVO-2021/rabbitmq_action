package me.itaowu.rabbitmq.client;

import com.rabbitmq.client.*;
import me.itaowu.rabbitmq.provider.RabbitMQProvider;
import me.itaowu.rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhanggf on 2017/4/23.
 */
public class RabbitMQClient {

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = ConnectionUtil.openConnection();
        channel.queueDeclare(RabbitMQProvider.QUEUE_NAME, false, false, false, null);
        //消息接收端
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String message = new String(body, "utf-8");
                System.out.println("client receive message content[" + message + "]");
            }
        };
        channel.basicConsume("", true, consumer);
    }

}

