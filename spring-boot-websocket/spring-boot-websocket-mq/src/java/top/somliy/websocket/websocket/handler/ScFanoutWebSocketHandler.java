package top.somliy.websocket.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import top.somliy.websocket.websocket.constants.WebSocketConstants;
import top.somliy.websocket.websocket.core.SessionExt;
import top.somliy.websocket.websocket.dto.FanoutDTO;

/**
 * 类名： @ClassName ScFanoutWebSocketHandler 消息处理器
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息处理器
 * 创建时间: 2023/10/8 11:49
 */
@Slf4j
@Component
public class ScFanoutWebSocketHandler extends AbstractScWebSocketHandler {
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("[websocket]文本消息：{}", message.toString());
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        log.info("[websocket]二进制消息：{}", message.toString());
    }

    @Override
    public void handleOpenFanout(SessionExt sessionExt, String key) {
        if (sessionExt != null) {
            FanoutDTO fanoutDTO = new FanoutDTO();
            fanoutDTO.setType(WebSocketConstants.STR_1);
            fanoutDTO.setKey(key);
//            RABBIT_MQ_PUSH_BEAN.sendJsonMsgFanout(fanoutDTO, RabbitMqConstants.ROUTING_KEY_WEBSOCKET_FANOUT);
        }
    }

    @Override
    public void handleCloseFanout(SessionExt sessionExt, String key) {
        FanoutDTO fanoutDTO = new FanoutDTO();
        fanoutDTO.setType(WebSocketConstants.STR_2);
        fanoutDTO.setKey(key);
//        RABBIT_MQ_PUSH_BEAN.sendJsonMsgFanout(fanoutDTO, RabbitMqConstants.ROUTING_KEY_WEBSOCKET_FANOUT);
    }
}
