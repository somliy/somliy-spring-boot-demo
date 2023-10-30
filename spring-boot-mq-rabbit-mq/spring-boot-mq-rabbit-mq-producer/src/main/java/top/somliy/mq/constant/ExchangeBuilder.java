package top.somliy.mq.constant;

/**
 * 类名： @ClassName ExchangeBuilder 交换机构造器
 * 创建人：@author zhao dong
 * 类描述：@Description: 交换机构造器
 * 创建时间: 2023/10/30 18:24
 */
public final class ExchangeBuilder {
    private final String baseString;

    private ExchangeBuilder(String baseString) {
        this.baseString = baseString;
    }

    public static ExchangeBuilder create(String baseString) {
        return new ExchangeBuilder(baseString);
    }

    public String getFanout() {
        return baseString + RabbitMqEmus.Fanout.getExchange();
    }

    public String getTopic() {
        return baseString + RabbitMqEmus.Topic.getExchange();
    }
}
