package top.somliy.websocket.websocket.mq;

import org.springframework.stereotype.Component;
import top.somliy.websocket.websocket.core.AbstractWebSocketServer;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 类名： @ClassName MqWebSocketServer 消息队列广播
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息队列广播
 * 创建时间: 2023/10/8 10:19
 */
@Component
public class MqWebSocketServer extends AbstractWebSocketServer {
    @Override
    public void onOpen(Session session, String key) {
        System.out.println(123);

    }

    @Override
    public void onMessage(String message, Session session, String key) {

    }

    @Override
    public void onClose(Session session, String key) {

    }

    @Override
    public void onError(Session session, Throwable throwable) {

    }
}
