package top.somliy.mq.simple.rabbitmq.core;

import cn.hutool.core.util.IdUtil;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类名： @ClassName RabbitMqConfig RabbitMqAdmin配置
 * 创建人：@author zhao dong
 * 类描述：@Description: RabbitMqAdmin配置
 * 创建时间: 2023/10/21 00:45
 */
@Component
public class RabbitMqAdmin {
    @Autowired
    private RabbitMqPushBean rabbitMqPushBean;
    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * 创建交换机，绑定
     *
     * @param exchangeStr 交换机
     * @param queueStr    队列
     * @param bindingStr  绑定
     */
    public void bindingQueue(String exchangeStr, String queueStr, String bindingStr) {
        this.bindingQueue(exchangeStr, queueStr, bindingStr, false);
    }

    /**
     * 创建交换机，绑定
     *
     * @param exchangeStr 交换机
     * @param queueStr    队列
     * @param bindingStr  绑定
     * @param autoDelete  自动删除
     */
    public void bindingQueue(String exchangeStr, String queueStr, String bindingStr, boolean autoDelete) {
        RabbitAdmin rabbitAdmin = rabbitMqPushBean.getRabbitAdmin();
        // 交换机
        Exchange exchange = new TopicExchange(exchangeStr);
        // 队列
        // Queue:名字 | durable: 是否持久化 | exclusive: 是否排它 | autoDelete: 是否自动删除
        Queue messagePushQueue = new Queue(queueStr, true, false, autoDelete);
        // 绑定
        Binding binding = BindingBuilder.bind(messagePushQueue).to(exchange).with(bindingStr).noargs();
        // setup
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(messagePushQueue);
        rabbitAdmin.declareBinding(binding);
    }


    public void registerListenerManualAck(RabbitMqMessageListener listener, String... queues) {
        String id = IdUtil.fastSimpleUUID();
        String beanName = "test-rabbit-mq-" + id;

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setBeanName(beanName);
        container.addQueueNames(queues);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(listener);

        container.start();
    }
}
