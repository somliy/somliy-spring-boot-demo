package top.somliy.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.somliy.mq.constant.RabbitMqConstant;

/**
 * 类名： @ClassName DirectExchangeConfiguration 交换机、路由键配置
 * 创建人：@author zhao dong
 * 类描述：@Description: 交换机、路由键配置
 * 创建时间: 2023/5/30 15:24
 */
@Configuration
public class DemoTopicExchangeConf {

    /**
     * 创建一个 Queue
     *
     * @return Queue
     */
    @Bean
    public Queue queueDemoTopic() {
        // Queue:名字 | durable: 是否持久化 | exclusive: 是否排它 | autoDelete: 是否自动删除
        return new Queue(RabbitMqConstant.QUEUE_TYPE_MESSAGE_PUSH.getTopicQueueSuffix(), true, false, false);
    }

    /**
     * 创建 Direct Exchange
     *
     * @return DirectExchange
     */
    @Bean
    public TopicExchange exchangeDemoTopic() {
        // name: 交换机名字 | durable: 是否持久化 | exclusive: 是否排它
        return new TopicExchange(RabbitMqConstant.EXCHANGE.getTopic(), true, false);
    }

    /**
     * 创建 Binding
     * Exchange：Message06.EXCHANGE
     * Routing key：Message06.ROUTING_KEY
     * Queue：Message06.QUEUE
     *
     * @return Binding
     */
    @Bean
    public Binding bindingDemoTopic() {
        return BindingBuilder.bind(queueDemoTopic()).to(exchangeDemoTopic())
                .with(RabbitMqConstant.ROUTING_KEY_MESSAGE_PUSH.getTopicRoutingKeySuffix());
    }
}
