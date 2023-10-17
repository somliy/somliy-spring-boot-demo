package top.somliy.websocket.websocket.fanout;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.somliy.websocket.websocket.constants.WebSocketConstants;
import top.somliy.websocket.websocket.core.SessionContainer;
import top.somliy.websocket.websocket.core.SessionExt;

/**
 * 类名： @ClassName HandlerFanoutWebSocketServer
 * 创建人：@author zhao dong
 * 类描述：@Description: 处理广播类
 * 创建时间: 2023/10/17 23:08
 */
@Slf4j
@Component
public class HandlerFanoutWebSocketServer implements HandlerFanoutWebSocket {
    private static final SessionContainer SESSION_CONTAINER = SpringUtil.getBean(SessionContainer.class);

    @Override
    public void handleOpenProcessing(String key, String uniqueId) {
        log.debug("[websocket]广播处理 创建连接");
        SessionExt sessionExt = SESSION_CONTAINER.getSessionExt(key);
        if (sessionExt != null) {
            String uniqueIdOld = sessionExt.getUniqueId();
            Integer version = sessionExt.getVersion();
            if (StrUtil.isNotEmpty(uniqueIdOld) && WebSocketConstants.INT_2.equals(version)) {
                sessionExt.closeSession();
                SESSION_CONTAINER.delSessionExt(key);
            } else {
                sessionExt.setVersion(WebSocketConstants.INT_2);
                SESSION_CONTAINER.addSessionExt(key, sessionExt);
            }
        }
    }
}
