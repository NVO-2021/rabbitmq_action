package me.itaowu.rabbitmq.provider;

import com.rabbitmq.client.Channel;
import me.itaowu.rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhanggf on 2017/4/23.
 */
public class RabbitMQProvider {

    public static final String QUEUE_NAME = "first queue";

    public static void main(String[] args) throws InterruptedException {
        Channel channel = null;
        try {
            channel = ConnectionUtil.openConnection();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for (int i = 0; i < 10; i++) {
                String message = "hello client , message send at " + System.currentTimeMillis() / 1000;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes("utf-8"));
                System.out.println("provider publish message [" + message + "]");
                Thread.sleep(1000);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeConnection(channel);
        }
    }
}
