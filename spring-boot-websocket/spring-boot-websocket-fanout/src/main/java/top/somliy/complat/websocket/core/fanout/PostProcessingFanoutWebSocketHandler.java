package top.somliy.complat.websocket.core.fanout;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.somliy.complat.websocket.constants.WebSocketConstants;
import top.somliy.complat.websocket.core.SessionContainer;
import top.somliy.complat.websocket.core.SessionExt;
import top.somliy.complat.websocket.dto.FanoutDTO;


/**
 * 类名： @ClassName PostProcessingFanoutWebSocketHandler websocket抽象类
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket抽象类
 * 创建时间: 2023/10/17 17:28
 */
@Slf4j
@Component
public class PostProcessingFanoutWebSocketHandler implements FanoutWebSocketHandler {
    private static final SessionContainer SESSION_CONTAINER = SpringUtil.getBean(SessionContainer.class);

    /**
     * 处理广播
     *
     * @param fanoutDTO 数据
     */
    public void handleFanoutMessage(FanoutDTO fanoutDTO) {
        String type = fanoutDTO.getType();
        String key = fanoutDTO.getKey();
        String uniqueId = fanoutDTO.getUniqueId();
        if (WebSocketConstants.STR_1.equals(type)) {
            log.debug("[websocket]广播，连接后置处理，key：" + key);
            this.handleOpenProcessing(key, uniqueId);
        } else {
            log.debug("[websocket]广播，关闭后置处理，key：" + key);
            this.handleCloseProcessing(key, uniqueId);
        }
    }

    @Override
    public void handleOpenProcessing(String key, String uniqueId) {
        SessionExt sessionExt = SESSION_CONTAINER.getSessionExt(key);
        if (sessionExt != null) {
            String uniqueIdOld = sessionExt.getUniqueId();
            if (!uniqueIdOld.equals(uniqueId)) {
                log.debug("[websocket]广播，连接后置处理，关闭用户之前连接");
                sessionExt.closeSession();
                SESSION_CONTAINER.delSessionExt(key);
            }
        }
        log.info("[websocket]广播，连接后置处理，完成");
    }

    @Override
    public void handleCloseProcessing(String key, String uniqueId) {
        log.debug("[websocket]广播处理 关闭连接，key：" + key);
        SessionExt sessionExt = SESSION_CONTAINER.getSessionExt(key);
        if (sessionExt != null) {
            String uniqueIdOld = sessionExt.getUniqueId();
            if (uniqueIdOld.equals(uniqueId)) {
                sessionExt.closeSession();
                SESSION_CONTAINER.delSessionExt(key);
            }
        }
        log.info("[websocket]广播，关闭后置处理，完成");
    }
}
