package top.somliy.mq.simple.rabbitmq.core;

import cn.hutool.json.JSONUtil;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.somliy.mq.simple.constant.RabbitMqConstant;
import top.somliy.mq.simple.rabbitmq.message.RabbitMqMessage;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 类名： @ClassName RabbitAdminConfig RabbitMqPushBean配置类
 * 创建人：@author zhao dong
 * 类描述：@Description: RabbitMqPushBean配置类
 * 创建时间: 2023/5/30 17:40
 */
@Component
public class RabbitMqPushBean {
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        rabbitAdmin = initRabbitAdmin();
    }

    /**
     * 获取实体
     *
     * @return RabbitAdmin
     */
    public RabbitAdmin getRabbitAdmin() {
        if (rabbitAdmin == null) {
            rabbitAdmin = initRabbitAdmin();
        }
        return rabbitAdmin;
    }

    /**
     * RabbitAdmin 可以方便的操作声明交换机，声明队列，绑定，清除消息，发送消息
     *
     * @return RabbitAdmin
     */
    public RabbitAdmin initRabbitAdmin() {
        rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    public void send(String data, String routingKey) {
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
}
