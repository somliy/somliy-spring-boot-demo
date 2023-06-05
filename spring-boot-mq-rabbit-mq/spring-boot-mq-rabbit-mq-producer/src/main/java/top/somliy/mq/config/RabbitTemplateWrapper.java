package top.somliy.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * 类名： @ClassName RabbitTemplateWrapper 消息发送增强
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息发送增强
 * 创建时间: 2023/5/30 14:37
 */
@Slf4j
@Configurable
public class RabbitTemplateWrapper {
    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * 初始化 RabbitTemplate 类
     * @return RabbitTemplate
     */
    @Bean
    public RabbitTemplate createRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        // 开启确认机制
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.error("发送到交换机失败，correlationData：" + correlationData + "，cause：" + cause);
            } else {
                log.info("发送到交换机失败，correlationData：" + correlationData + "，cause：" + cause);
            }
        });

        // 将开启returnedMessage属性并添加ReturnCallback
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.info("[确认消息送到队列(Queue)结果：][回应码:{}][回应信息:{}][交换机:{}][路由键:{}][发生消息:{}]",
                    returnedMessage.getReplyCode(), returnedMessage.getReplyText(), returnedMessage.getExchange(),
                    returnedMessage.getRoutingKey(), returnedMessage.getMessage());
        });
        return rabbitTemplate;
    }
}