package top.somliy.websocket.websocket.fanout;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import top.somliy.websocket.websocket.constants.WebSocketConstants;
import top.somliy.websocket.websocket.core.SessionContainer;
import top.somliy.websocket.websocket.core.SessionExt;
import top.somliy.websocket.websocket.dto.FanoutDTO;
import top.somliy.websocket.websocket.util.WebSocketUtil;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * 类名： @ClassName AbstractFanoutWebSocketHandler websocket抽象类
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket抽象类
 * 创建时间: 2023/10/17 17:28
 */
@Slf4j
@Component
public class ScFanoutWebSocketHandler implements FanoutWebSocketHandler {
    private static final SessionContainer SESSION_CONTAINER = SpringUtil.getBean(SessionContainer.class);

    /**
     * 处理广播
     *
     * @param fanoutDTO 数据
     */
    public void handleFanoutMessage(FanoutDTO fanoutDTO) {
        String type = fanoutDTO.getType();
        String key = fanoutDTO.getKey();
        if (WebSocketConstants.STR_1.equals(type)) {
            this.handleOpenProcessing(key);
        } else {
            this.handleCloseProcessing(key);
        }
    }

    @Override
    public void handleOpenProcessing(String key) {
        log.debug("[websocket]广播处理 创建连接");
        SessionExt sessionExt = SESSION_CONTAINER.getSessionExt(key);
        if (sessionExt != null) {
            Integer version = sessionExt.getVersion();
            if (WebSocketConstants.INT_1.equals(version)) {
                sessionExt.setVersion(WebSocketConstants.INT_2);
                SESSION_CONTAINER.addSessionExt(key, sessionExt);
            } else {
                sessionExt.closeSession();
                SESSION_CONTAINER.delSessionExt(key);
            }
        }
    }

    @Override
    public void handleCloseProcessing(String key) {
        log.debug("[websocket]广播处理 关闭连接");
        SessionExt sessionExt = SESSION_CONTAINER.getSessionExt(key);
        if (sessionExt != null) {
            sessionExt.closeSession();
            SESSION_CONTAINER.delSessionExt(key);
        }
    }
}
