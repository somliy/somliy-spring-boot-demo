package top.somliy.mq.constant;

/**
 * 类名： @ClassName RabbitMqEmus 枚举
 * 创建人：@author zhao dong
 * 类描述：@Description: 枚举
 * 创建时间: 2023/10/30 17:37
 */
public enum RabbitMqEmus {
    /**
     * Fanout
     */
    Fanout("Fanout", ".fanout", "_fanout", ".fanout"),
    /**
     * Topic
     */
    Topic("Topic", ".topic", "_topic", ".topic");
    /**
     * code
     */
    private final String code;
    private final String exchange;
    private final String queue;
    private final String routingKey;

    RabbitMqEmus(String code, String exchange, String queue, String routingKey) {
        this.code = code;
        this.exchange = exchange;
        this.queue = queue;
        this.routingKey = routingKey;
    }

    public String getCode() {
        return code;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public String getQueue() {
        return queue;
    }

    public String getExchange() {
        return exchange;
    }
}
