package top.somliy.complat.websocket.rabbitmq.producer;

import cn.hutool.json.JSONUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.somliy.complat.websocket.rabbitmq.constant.RabbitMqConstant;
import top.somliy.complat.websocket.rabbitmq.message.RabbitMqMessage;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 类名： @ClassName RabbitMqProduceBean 生产者
 * 创建人：@author zhao dong
 * 类描述：@Description: 生产者
 * 创建时间: 2023/5/30 15:04
 */
@Component
public class RabbitMqProduceBean {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /***
     * 发送消息
     * @param obj 消息
     * @param routingKey 路由键
     */
    public void syncSend(Object obj, String routingKey) {
        String data = JSONUtil.toJsonStr(obj);
        // 创建消息
        RabbitMqMessage message = new RabbitMqMessage();
        message.setData(data);
        String uuid = UUID.randomUUID().toString();
        message.setId(uuid);
        message.setSendTime(LocalDateTime.now());
        // 同步发送消息
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE, routingKey, message);
    }

    /***
     * 发送消息
     * @param obj 消息
     */
    public void syncFanoutSend(Object obj) {
        String data = JSONUtil.toJsonStr(obj);
        // 创建消息
        RabbitMqMessage message = new RabbitMqMessage();
        message.setData(data);
        String uuid = UUID.randomUUID().toString();
        message.setId(uuid);
        message.setSendTime(LocalDateTime.now());
        // 同步发送消息
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE_FANOUT, "", message);
    }
}
