package top.somliy.mq.simple.rabbitmq.message;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.nio.charset.StandardCharsets;

/**
 * 类名： @ClassName JsonMessage json消息
 * 创建人：@author zhao dong
 * 类描述：@Description: json消息
 * 创建时间: 2023/10/23 17:13
 */
@Slf4j
public class JsonMessage extends Message {
    public JsonMessage(byte[] body) {
        super(body);
    }

    public JsonMessage(byte[] body, MessageProperties messageProperties) {
        super(body, messageProperties);
    }

    public static JsonMessage createJsonMessage(Object obj) {
        String jsonStr = JSONUtil.toJsonStr(obj);
        JsonMessage jsonMessage = null;
        if (CharSequenceUtil.isNotEmpty(jsonStr)) {
            byte[] bytes = jsonStr.getBytes(StandardCharsets.UTF_8);
            jsonMessage = new JsonMessage(bytes);
        }
        return jsonMessage;
    }
}
