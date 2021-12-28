# ActiveMQ

```text
ACK 消息确认 Acknowledge
- Broker 当前提供服务的MQ端

- Queues 点对点 只能被一次读取 消息队列
- Topics 主题

- Subscribers 一些订阅的消息
- Connections 链接的信息（支持的协议）
- Network 桥接的一种方式 组集群 MQ作为节点加入到集群中（分布式、高可用）
- Scheduled 任务调度

- Send：
    Send a JMS Message:
        Destination: 发送目标地
        Queue or Topics：消息模式，二选一
        ... ...
    Send Message Body: 消息体
```

### 消息的创建&发送
```text
- Queue and Topic
@link Sender
```

### 消息的接收
```text
- Queue and Topic
@link Receiver
```

### more
```text
- 配置信息 & API
    - 死信队列、独占消费、分组消费、消息类型
```