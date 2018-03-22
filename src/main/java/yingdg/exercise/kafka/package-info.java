/**
 * Created by yingdg on 2017/10/26 0026.
 */
package yingdg.exercise.kafka;

/*
Kafka 专用术语

Broker：Kafka 集群包含一个或多个服务器，这种服务器被称为 broker。

Topic：每条发布到 Kafka 集群的消息都有一个类别，这个类别被称为 Topic。
            （物理上不同 Topic 的消息分开存储，逻辑上一个 Topic 的消息虽然保存于一个或多个 broker 上，
            但用户只需指定消息的 Topic 即可生产或消费数据而不必关心数据存于何处）。

Partition：Partition 是物理上的概念，每个 Topic 包含一个或多个 Partition。

Producer：负责发布消息到 Kafka broker。

Hello：消息消费者，向 Kafka broker 读取消息的客户端。

Hello Group：每个 Hello 属于一个特定的 Hello Group
            （可为每个 Hello 指定 group name，若不指定 group name 则属于默认的 group）。
 */