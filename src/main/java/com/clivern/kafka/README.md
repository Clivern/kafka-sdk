### Usage

Create a Kafka Topic:

```java
import java.util.HashMap;
import com.clivern.kafka.Configs;
import com.clivern.kafka.Utils;


HashMap<String, String> map = new HashMap<String, String>();
map.put("bootstrap.servers", "localhost:9092");
Utils.createTopic("clivern", Configs.fromMap(map));
```

Kafka Producer:

```java
import com.clivern.kafka.Configs;
import com.clivern.kafka.Producer;
import com.clivern.kafka.Kafka;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


Configs configs = new Configs();
configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

Producer producer = (new Kafka()).newProducer(configs);

for (int i = 0; i < 10; i++) {
    ProducerRecord<String, String> record =
            new ProducerRecord<>("clivern", null, "Hello World " + i);

    producer.send(record).flush();
}

producer.close();
```

Kafka Consumer:

```java
import com.clivern.kafka.Configs;
import com.clivern.kafka.Consumer;
import com.clivern.kafka.Kafka;
import com.clivern.kafka.CallbackInterface;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;


Configs configs = new Configs();

configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
configs.put(ConsumerConfig.GROUP_ID_CONFIG, "clivern");
configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

Consumer consumer = (new Kafka()).newConsumer(configs);

CallbackInterface<ConsumerRecord<String, String>> callback = (record) -> {
    System.out.println("Message received: " + record.value());
};

consumer.subscribe("clivern")
        .handler(callback).run(true);
```

Pub/Sub Pattern:

```java
import com.clivern.kafka.event.MessagePublisher;
import com.clivern.kafka.event.Observer;
import com.clivern.kafka.event.Message;
import com.clivern.kafka.exception.MissingEvent;


try {
    MessagePublisher messagePublisher = new MessagePublisher();

    Observer callback1 =
            (message) -> {
                System.out.println("New Order with Payload:" + message.getContent());
            };

    Observer callback2 =
            (message) -> {
                System.out.println("New Notification with Payload:" + message.getContent());
            };

    messagePublisher.attach("service.newOrder", callback1);
    messagePublisher.attach("service.newNotification", callback2);
    messagePublisher.attach("service.newUser", (message) -> {
        System.out.println("New User with Info:" + message.getContent());
    });

    messagePublisher.notify("service.newOrder", new Message("{}"));
    messagePublisher.notify("service.newNotification", new Message("{}"));
    messagePublisher.notify("service.newUser", new Message("{}"));
} catch(MissingEvent e) {
    // Do something about it
}
```
