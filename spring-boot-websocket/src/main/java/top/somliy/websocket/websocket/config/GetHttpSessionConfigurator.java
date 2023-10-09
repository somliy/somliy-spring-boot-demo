//package top.somliy.websocket.websocket.config;
//
//import lombok.extern.slf4j.Slf4j;
//import top.somliy.websocket.websocket.constants.WebSocketConstants;
//
//import javax.websocket.HandshakeResponse;
//import javax.websocket.server.HandshakeRequest;
//import javax.websocket.server.ServerEndpointConfig;
//import java.util.List;
//
///**
// * 类名： @ClassName GetHttpSessionConfigurator 获取session处理
// * 创建人：@author zhao dong
// * 类描述：@Description: 获取session处理
// * 创建时间: 2023/10/8 22:47
// */
//@Slf4j
//public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
//
//    @Override
//    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
//        // 获取认证信息并验证用户角色等权限信息
//        List<String> strings = request.getParameterMap().get(WebSocketConstants.STR_KEY);
//        log.info("session" + strings.toString());
//    }
//}
