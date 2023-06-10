# 从零开始，5分钟轻松实现Spring Boot与RabbitMQ的无缝集成

## 🌏 环境
- docker v4.16.2
- springboot 2.7.0
- RabbitMQ 3.9.1
  - rabbitmq_delayed_message_exchange 3.9.0

ps：代码地址  [gitee](https://gitee.com/somliy/somliy-spring-boot-demo/tree/master/spring-boot-mq-rabbit-mq)

## 🪜 服务架构
使用maven多模块，将生产者、消费者分别以springboot项目启动，两者通过RabbitMQ进行消息通信。

![[Pasted image 20230610161606.png]]

### 消息发送方
spring-boot-mq-rabbit-mq-producer
在这创建路由、队列、交换机，并进行消息的发送，分为普通消息和延迟消息。开启消息的ack，可以对失败的消息进行重复发送。


### 消息接收方
spring-boot-mq-rabbit-mq-consumer
消息接收方需要对监听的队列进行创建，需要注意的是消息可能重复发送，需要对接口进行幂等性处理。


## 🍚 食用方法
1. 运行docker-compose.yml启动环境
2. 启动生产者SpringBootRabbitMqProducerApplication.java
3. 启动消费者SpringBootRabbitMqConsumerApplication.java
4. 等待日志打印


## 🪛 自定义配置项
### 路由、交换机、队列

#### RabbitAdminConfig.java
- 配置`RabbitAdmin`方法，能够动态加载 路由、队列、交换机

#### DirectExchangeConf.java
交换机、路由键、队列配置
- 本示例是根据完整路由键进行消息推送示例
- 示例中每个不同的路由、队列、交换机可以根据需要添加相应的方法进行组合

#### RabbitTemplateWrapper.java
- 增强`RabbitTemplate`，使之能够开启确认机制
- 示例中每个不同的路由、队列、交换机可以根据需要添加相应的方法进行组合

### 延迟消息
创建交换机时，配置交换机参数，使之延迟发送
```  
// 交换机  
Map<String, Object> args = new HashMap<>(1);  
args.put("x-delayed-type", "direct");  
Exchange exchange =  
        new CustomExchange(RabbitMqConstant.EXCHANGE_DELAYED, "x-delayed-message", true, false, args);
```  

封装消息发送，添加消息实体类，增强消息内容，添加id和时间信息，同时设置过期时间
```java  
/***  
 * 发送延时消息  
 * @param data 消息  
 * @param routingKey 路由键  
 * @param delay  时间  
 */  
public void syncSendDelayed(String data, String routingKey, int delay) {  
    // 创建消息  
    RabbitMqMessage message = new RabbitMqMessage();  
    message.setData(data);  
    String uuid = UUID.randomUUID().toString();  
    message.setId(uuid);  
    message.setSendTime(LocalDateTime.now());  
    String messageStr = JSONUtil.toJsonStr(message);  
    MessagePostProcessor postProcessor = postProcessMessage -> {  
        // 设置过期时间  
        postProcessMessage.getMessageProperties().setHeader("x-delay", delay);  
        return postProcessMessage;  
    };  
    // 发送延时消息  
    rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE_DELAYED, routingKey, messageStr, postProcessor);  
}
```  

## 📚 测试效果
消息发送
![[Pasted image 20230610164403.png]]
数据接收
![[Pasted image 20230610164452.png]]
## 常见问题

### vhost not fond 问题
Caused by: com.rabbitmq.client.ShutdownSignalException: connection error; protocol method: #method<connection.close>(reply-code=530, reply-text=NOT_ALLOWED - vhost /test not found, class-id=10, method-id=40)  
at com.rabbitmq.utility.ValueOrException.getValue(ValueOrException.java:66)  
at com.rabbitmq.utility.BlockingValueOrException.uninterruptibleGetValue(BlockingValueOrException.java:36)  
at com.rabbitmq.client.impl.AMQChannel$BlockingRpcContinuation.getReply(AMQChannel.java:502)  
at com.rabbitmq.client.impl.AMQChannel.privateRpc(AMQChannel.java:293)  
at com.rabbitmq.client.impl.AMQChannel.exnWrappingRpc(AMQChannel.java:141)  
... 108 more

需要在`RabbitMq`管理页面，Admin->Virtual Hosts 创建