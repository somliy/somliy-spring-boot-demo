package top.somliy.mq.simple.rabbitmq.core;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * 类名： @ClassName RabbitMqMessageListener 消息监听
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息监听
 * 创建时间: 2023/10/23 11:46
 */
@Slf4j
public abstract class RabbitMqMessageListener implements ChannelAwareMessageListener {

    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            MessageProperties messageProperties = message.getMessageProperties();
            String contentType = messageProperties.getContentType();
            if ("application/json".equals(contentType)) {
                this.onJsonMessage(message, channel);
            }
            // 处理消息的逻辑
            log.info("Received message: " + new String(message.getBody()));

            // 手动确认消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 发生异常时，拒绝消息并重新入队
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    /**
     * json 数据
     *
     * @param message 消息
     * @param channel channel
     */
    public abstract void onJsonMessage(Message message, Channel channel);
}
