package top.somliy.websocket.websocket.util;

import org.springframework.web.socket.WebSocketSession;
import top.somliy.websocket.websocket.constants.WebSocketConstants;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 类名： @ClassName WebSocketUtil 工具类
 * 创建人：@author zhao dong
 * 类描述：@Description: 工具类
 * 创建时间: 2023/10/17 17:53
 */
public final class WebSocketUtil {

    private WebSocketUtil() {
    }

    public static Map<String, String> getParamsFromURI(URI uri) {
        Map<String, String> params = new HashMap<>(WebSocketConstants.INT_2);
        String query = uri.getQuery();
        if (query != null) {
            String[] keyValuePairs = query.split("&");
            for (String keyValuePair : keyValuePairs) {
                String[] keyValue = keyValuePair.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    params.put(key, value);
                }
            }
        }
        return params;
    }

    /**
     * 获取请求参数
     *
     * @param session session
     * @return 参数
     */
    public static String getUriParamKey(WebSocketSession session) {
        URI uri = session.getUri();
        Map<String, String> paramsFrom = WebSocketUtil.getParamsFromURI(Objects.requireNonNull(uri));
        return paramsFrom.get(WebSocketConstants.STR_KEY);
    }
}
