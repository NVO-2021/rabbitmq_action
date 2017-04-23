package me.itaowu.rabbitmq.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhanggf on 2017/4/23.
 */
public class ConnectionUtil {
    public static ConnectionFactory factory = new ConnectionFactory();

    public static Channel openConnection() throws IOException, TimeoutException {
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        if (null != channel)
            return channel;
        else return null;
    }


    public static void closeConnection(Channel channel) {
        if (null == channel)
            return;
        Connection connection = channel.getConnection();
        try {
            channel.close();
            System.out.println("channel has been closed!");
        } catch (Exception e) {

        }

        try {
            if (null != connection)
                connection.close();
            System.out.println("connection has been closed!");
        } catch (Exception e) {

        }

    }
}
