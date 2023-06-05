package top.somliy.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.somliy.mq.constant.RabbitMqConstant;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名： @ClassName DirectExchangeConf 交换机、路由键配置
 * 创建人：@author zhao dong
 * 类描述：@Description: 交换机、路由键配置
 * 创建时间: 2023/5/30 15:24
 */
@Component
public class DirectExchangeConf {
    @Autowired
    private RabbitAdminConfig rabbitAdminConfig;

    /**
     * 初始化交换机，队列，绑定路由键
     */
    @PostConstruct
    public void init() {
        RabbitAdmin rabbitAdmin = rabbitAdminConfig.getRabbitAdmin();
        this.testConfig(rabbitAdmin);
        this.testDelayedConfig(rabbitAdmin);
    }

    /**
     * 配置延时
     *
     * @param rabbitAdmin 配置
     */
    private void testDelayedConfig(RabbitAdmin rabbitAdmin) {
        // 交换机
        Map<String, Object> args = new HashMap<>(1);
        args.put("x-delayed-type", "direct");
        Exchange exchange =
                new CustomExchange(RabbitMqConstant.EXCHANGE_DELAYED, "x-delayed-message", true, false, args);
        // 队列
        // Queue:名字 | durable: 是否持久化 | exclusive: 是否排它 | autoDelete: 是否自动删除
        Queue messagePushQueue = new Queue(RabbitMqConstant.QUEUE_TYPE_MESSAGE_PUSH_DELAYED, true, false, false);

        // 绑定
        Binding binding = BindingBuilder.bind(messagePushQueue).to(exchange)
                .with(RabbitMqConstant.ROUTING_KEY_MESSAGE_PUSH_DELAYED).noargs();

        // setup
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(messagePushQueue);
        rabbitAdmin.declareBinding(binding);
    }

    /**
     * 配置普通队列
     *
     * @param rabbitAdmin 配置
     */
    private void testConfig(RabbitAdmin rabbitAdmin) {
        // 交换机
        Exchange exchange = new TopicExchange(RabbitMqConstant.EXCHANGE);
        // 队列
        // Queue:名字 | durable: 是否持久化 | exclusive: 是否排它 | autoDelete: 是否自动删除
        Queue messagePushQueue = new Queue(RabbitMqConstant.QUEUE_TYPE_MESSAGE_PUSH, true, false, false);
        // 绑定
        Binding binding =
                BindingBuilder.bind(messagePushQueue).to(exchange).with(RabbitMqConstant.ROUTING_KEY_MESSAGE_PUSH)
                        .noargs();
        // setup
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(messagePushQueue);
        rabbitAdmin.declareBinding(binding);
    }
}
