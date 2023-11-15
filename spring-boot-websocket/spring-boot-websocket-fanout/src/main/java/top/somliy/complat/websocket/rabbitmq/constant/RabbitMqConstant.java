package top.somliy.complat.websocket.rabbitmq.constant;

/**
 * 类名： @ClassName RabbitMqConstant 消息队列
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息队列
 * 创建时间: 2023/5/30 15:08
 */
public class RabbitMqConstant {
    /**
     * 交换机名称
     */
    public static final String EXCHANGE = "top.somliy.rabbit.mq.test";
    public static final String EXCHANGE_FANOUT = "top.somliy.rabbit.mq.test.fanout";
    /**
     * 路由键信息
     */
    public static final String QUEUE_TYPE_MESSAGE_PUSH_FANOUT = "queue_type_message_push_test_fanout";
    /**
     * 十秒QUEUE_TYPE_MESSAGE_PUSH_FANOUT
     */
    public static final Integer TEN_SECONDS = 10 * 1000;

    private RabbitMqConstant() {
    }
}
