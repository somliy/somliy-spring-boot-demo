package top.somliy.mq.producer;

import cn.hutool.json.JSONUtil;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.somliy.mq.constant.RabbitMqConstant;
import top.somliy.mq.message.RabbitMqMessage;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 类名： @ClassName rabbitTemplatePush 生产者
 * 创建人：@author zhao dong
 * 类描述：@Description: 生产者
 * 创建时间: 2023/5/30 15:04
 */
@Component
public class RabbitTemplatePush {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /***
     * 发送消息
     * @param data 消息
     * @param routingKey 路由键
     */
    public void syncSend(String data, String routingKey) {
        // 创建消息
        RabbitMqMessage message = new RabbitMqMessage();
        message.setData(data);
        String uuid = UUID.randomUUID().toString();
        message.setId(uuid);
        message.setSendTime(LocalDateTime.now());
        String messageStr = JSONUtil.toJsonStr(message);
        // 同步发送消息
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE, routingKey, messageStr);
    }

    /***
     * 发送延时消息
     * @param data 消息
     * @param routingKey 路由键
     * @param delay  时间
     */
    public void syncSendDelayed(String data, String routingKey, int delay) {
        // 创建消息
        RabbitMqMessage message = new RabbitMqMessage();
        message.setData(data);
        String uuid = UUID.randomUUID().toString();
        message.setId(uuid);
        message.setSendTime(LocalDateTime.now());
        String messageStr = JSONUtil.toJsonStr(message);
        MessagePostProcessor postProcessor = postProcessMessage -> {
            // 设置过期时间
            postProcessMessage.getMessageProperties().setHeader("x-delay", delay);
            return postProcessMessage;
        };
        // 发送延时消息
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE_DELAYED, routingKey, messageStr, postProcessor);
    }
}
