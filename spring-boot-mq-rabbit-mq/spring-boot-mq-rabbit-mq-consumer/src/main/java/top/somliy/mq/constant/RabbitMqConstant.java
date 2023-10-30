package top.somliy.mq.constant;

/**
 * 类名： @ClassName RabbitMqConstant 消息队列
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息队列
 * 创建时间: 2023/5/30 15:08
 */
public final class RabbitMqConstant {
    /**
     * 示例队列
     */
    public static final String QUEUE_TYPE_MESSAGE_PUSH = "queue_type_message_push_test_topic";
    /**
     * 示例队列，延时
     */
    public static final String QUEUE_TYPE_MESSAGE_PUSH_DELAYED = "queue_type_message_push_delayed_test";

    private RabbitMqConstant() {
    }
}
