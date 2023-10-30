package top.somliy.mq.constant;

/**
 * 类名： @ClassName RabbitMqEmus 枚举
 * 创建人：@author zhao dong
 * 类描述：@Description: 枚举
 * 创建时间: 2023/10/30 17:37
 */
public enum RabbitMqEmus {
    /**
     * Direct
     */
    Direct("Direct", ".direct", "_direct", ".direct"),
    /**
     * Fanout
     */
    Fanout("Fanout", ".fanout", "_fanout", ".fanout"),
    /**
     * Headers
     */
    Headers("Headers", ".headers", "_headers", ".headers"),
    /**
     * Topic
     */
    Topic("Topic", ".topic", "_topic", ".topic");
    /**
     * code
     */
    private final String code;
    private final String routingKey;
    private final String queue;
    private final String exchange;

    RabbitMqEmus(String code, String routingKey, String queue, String exchange) {
        this.code = code;
        this.routingKey = routingKey;
        this.queue = queue;
        this.exchange = exchange;
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
