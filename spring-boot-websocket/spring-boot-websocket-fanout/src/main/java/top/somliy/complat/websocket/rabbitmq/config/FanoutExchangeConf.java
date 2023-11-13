package top.somliy.complat.websocket.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.somliy.complat.websocket.rabbitmq.constant.RabbitMqConstant;

/**
 * 类名： @ClassName FanoutExchangeConf 交换机、路由键配置
 * 创建人：@author zhao dong
 * 类描述：@Description: 交换机、路由键配置
 * 创建时间: 2023/5/30 15:24
 */
@Configuration
public class FanoutExchangeConf {

    /**
     * 创建一个 Queue
     *
     * @return Queue
     */
    @Bean
    public Queue queueFanout() {
        // Queue:名字 | durable: 是否持久化 | exclusive: 是否排它 | autoDelete: 是否自动删除
        return new Queue(RabbitMqConstant.QUEUE_TYPE_MESSAGE_PUSH_FANOUT, true, false, true);
    }

    /**
     * 创建 Direct Exchange
     *
     * @return DirectExchange
     */
    @Bean
    public FanoutExchange exchangeFanout() {
        // name: 交换机名字 | durable: 是否持久化 | exclusive: 是否排它
        return new FanoutExchange(RabbitMqConstant.EXCHANGE_FANOUT, true, false);
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
    public Binding bindingDirect() {
        return BindingBuilder.bind(queueFanout()).to(exchangeFanout());
    }
}
