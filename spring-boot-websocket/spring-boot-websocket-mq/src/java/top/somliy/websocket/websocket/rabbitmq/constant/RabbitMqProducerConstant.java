package top.somliy.websocket.websocket.rabbitmq.constant;

/**
 * 类名： @ClassName RabbitMqProducerConstant 消息队列
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息队列
 * 创建时间: 2023/5/30 15:08
 */
public class RabbitMqProducerConstant {
    /**
     * 交换机名称
     */
    public static final String EXCHANGE = "top.somliy.rabbit.mq.test";
    public static final String EXCHANGE_DELAYED = "top.somliy.rabbit.mq.delayed.test";
    /**
     * 路由键信息
     */
    public static final String ROUTING_KEY_MESSAGE_PUSH = "routing.key.message.push.test";
    public static final String QUEUE_TYPE_MESSAGE_PUSH = "queue_type_message_push_test";
    /**
     * 路由键信息，延时
     */
    public static final String ROUTING_KEY_MESSAGE_PUSH_DELAYED = "routing.key.message.push.delayed.test";
    public static final String QUEUE_TYPE_MESSAGE_PUSH_DELAYED = "queue_type_message_push_delayed_test";
    /**
     * 十秒
     */
    public static final Integer TEN_SECONDS = 10 * 1000;

    private RabbitMqProducerConstant() {
    }
}
