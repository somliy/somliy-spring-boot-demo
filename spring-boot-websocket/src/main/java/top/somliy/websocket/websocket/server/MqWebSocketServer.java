package top.somliy.websocket.websocket.server;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.somliy.websocket.websocket.core.AbstractWebSocketServer;
import top.somliy.websocket.websocket.core.SessionExt;
import top.somliy.websocket.websocket.dto.FanoutDTO;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 类名： @ClassName MqWebSocketServer 消息队列广播
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息队列广播
 * 创建时间: 2023/10/8 10:19
 */
@Slf4j
@Component
public class MqWebSocketServer extends AbstractWebSocketServer {
    private static final RabbitMqPushBean RABBIT_MQ_PUSH_BEAN = SpringUtil.getBean(RabbitMqPushBean.class);

    @Override
    public void onOpen(Session session, String key) {
        log.info("[websocket]onOpen钩子");
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

    @Override
    public void handleFanout(SessionExt sessionExt, String key) {
        if (sessionExt != null) {
            String uniqueId = sessionExt.getUniqueId();
            FanoutDTO fanoutDTO = new FanoutDTO();
            fanoutDTO.setKey(key);
            fanoutDTO.setUniqueId(uniqueId);
//            RABBIT_MQ_PUSH_BEAN.sendJsonMsgFanout(fanoutDTO, RabbitMqConstants.ROUTING_KEY_WEBSOCKET_FANOUT);
        }
    }
}
