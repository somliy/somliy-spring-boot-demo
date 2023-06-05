package top.somliy.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import top.somliy.mq.constant.RabbitMqConstant;

/**
 * 类名： @ClassName RabbitMqConsumer 消费者
 * 创建人：@author zhao dong
 * 类描述：@Description: 消费者
 * 创建时间: 2023/5/30 16:35
 */
@Slf4j
@Component
public class RabbitMqConsumer {
    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean("test")
    public SimpleMessageListenerContainer messageContainerTest() {
        // 创建SimpleMessageListenerContainer容器
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        // 设置连接工厂
        container.setConnectionFactory(connectionFactory);
        // 设置监听队列名字
        container.setQueueNames(RabbitMqConstant.QUEUE_TYPE_MESSAGE_PUSH);
        // 设置手动ACK模式
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置消息监听器
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            try {
                String messageContent = new String(message.getBody());
                // 处理收到的消息
                log.info("【Received message】: " + messageContent);
                // 手动ACK
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception e) {
                // 发生异常时，调用 reject（或 nack）方法拒绝消息，并进入错误处理流程
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }
        });
        return container;
    }

    @Bean("delayed")
    public SimpleMessageListenerContainer messageContainer() {
        // 创建SimpleMessageListenerContainer容器
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        // 设置连接工厂
        container.setConnectionFactory(connectionFactory);
        // 设置监听队列名字
        container.setQueueNames(RabbitMqConstant.QUEUE_TYPE_MESSAGE_PUSH_DELAYED);
        // 设置手动ACK模式
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置消息监听器
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            try {
                String messageContent = new String(message.getBody());
                // 处理收到的消息
                log.info("【Received message】: " + messageContent);
                // 手动ACK
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception e) {
                // 发生异常时，调用 reject（或 nack）方法拒绝消息，并进入错误处理流程
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }
        });
        return container;
    }
}
