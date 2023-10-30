package top.somliy.mq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.somliy.mq.constant.RabbitMqConstant;
import top.somliy.mq.message.RabbitMqMessage;

import java.io.IOException;

/**
 * 类名： @ClassName RabbitMqConsumer 消费者
 * 创建人：@author zhao dong
 * 类描述：@Description: 消费者
 * 创建时间: 2023/5/30 16:35
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConstant.QUEUE_TYPE_MESSAGE_PUSH)
public class RabbitMqConsumer {
    @RabbitHandler
    public void onMessageAck(RabbitMqMessage rabbitMqMessage, Message message, Channel channel) throws IOException {
        try {
            log.info("[RabbitMqConsumer onMessageAck][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(),
                    rabbitMqMessage);
            //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
            final long deliveryTag = message.getMessageProperties().getDeliveryTag();
            // 取当前时间，达到一个随机效果，测试的话可以多跑几次试试
            if (System.currentTimeMillis() % 2 == 1) {
                // 通知 MQ 消息已被成功消费,可以ACK了
                // 第二个参数 multiple ，用于批量确认消息，为了减少网络流量，手动确认可以被批处。
                // 1. 当 multiple 为 true 时，则可以一次性确认 deliveryTag 小于等于传入值的所有消息
                // 2. 当 multiple 为 false 时，则只确认当前 deliveryTag 对应的消息
                channel.basicAck(deliveryTag, false);
                log.info("[RabbitMqConsumer onMessageAck][正常ack:{}]", rabbitMqMessage);
            } else {
                log.info("[RabbitMqConsumer onMessageAck][未ack:{}]", rabbitMqMessage);
                throw new RuntimeException("手动异常");
            }
        } catch (Exception e) {
            // 处理失败,重新压入MQ
            channel.basicRecover();
            log.info("[RabbitMqConsumer onMessageAck][消息重新压入MQ:{}]", rabbitMqMessage);
        }
    }
}
