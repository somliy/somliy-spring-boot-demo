package top.somliy.websocket.websocket.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 类名： @ClassName WebSocketProperty websocket配置文件
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket配置文件
 * 创建时间: 2023/10/8 09:55
 */
@Configuration
@ConfigurationProperties(prefix = "project.websocket")
public class WebSocketProperty {
    /**
     * 超时时间
     */
    private Long timeoutPeriod;
    /**
     * 传入文本消息的最大长度
     */
    private Integer textMessageSize;
    /**
     * 二进制消息的最大长度
     */
    private Integer binaryMessageSize;

    public Long getTimeoutPeriod() {
        return timeoutPeriod;
    }

    public void setTimeoutPeriod(Long timeoutPeriod) {
        this.timeoutPeriod = timeoutPeriod;
    }

    public Integer getTextMessageSize() {
        return textMessageSize;
    }

    public void setTextMessageSize(Integer textMessageSize) {
        this.textMessageSize = textMessageSize;
    }

    public Integer getBinaryMessageSize() {
        return binaryMessageSize;
    }

    public void setBinaryMessageSize(Integer binaryMessageSize) {
        this.binaryMessageSize = binaryMessageSize;
    }
}
