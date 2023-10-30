package top.somliy.mq.constant;

/**
 * 类名： @ClassName SuffixBuilder 后缀构造器
 * 创建人：@author zhao dong
 * 类描述：@Description: 后缀构造器
 * 创建时间: 2023/10/30 17:47
 */
public final class SuffixBuilder {
    private final String baseString;

    private SuffixBuilder(String baseString) {
        this.baseString = baseString;
    }

    public static SuffixBuilder create(String baseString) {
        return new SuffixBuilder(baseString);
    }

    public String getTopicQueueSuffix() {
        return baseString + RabbitMqEmus.Topic.getQueue();
    }

    public String getTopicRoutingKeySuffix() {
        return baseString + RabbitMqEmus.Topic.getRoutingKey();
    }

    public String getDirectQueueSuffix() {
        return baseString + RabbitMqEmus.Direct.getQueue();
    }

    public String getDirectRoutingKeySuffix() {
        return baseString + RabbitMqEmus.Direct.getRoutingKey();
    }

    public String getFanoutQueueSuffix() {
        return baseString + RabbitMqEmus.Fanout.getQueue();
    }

    public String getFanoutRoutingKeySuffix() {
        return baseString + RabbitMqEmus.Fanout.getRoutingKey();
    }

    public String getHeadersQueueSuffix() {
        return baseString + RabbitMqEmus.Headers.getQueue();
    }

    public String getHeadersRoutingKeySuffix() {
        return baseString + RabbitMqEmus.Headers.getRoutingKey();
    }
}
