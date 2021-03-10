<p align="center">
	<img alt="kafka-sdk Logo" src="/images/logo.png" height="150" />
	<h3 align="center">Kafka SDK</h3>
	<p align="center">A Java SDK for Apache Kafka.</p>
	<p align="center">
        <a href="https://github.com/Clivern/kafka-sdk/actions/workflows/ci.yml">
            <img src="https://github.com/Clivern/kafka-sdk/actions/workflows/ci.yml/badge.svg">
        </a>
		<a href="http://www.javadoc.io/doc/com.clivern/kafka-sdk">
            <img src="http://www.javadoc.io/badge/com.clivern/kafka-sdk.svg">
        </a>
		<a href="https://mvnrepository.com/artifact/com.clivern/kafka-sdk/0.1.0">
            <img src="https://img.shields.io/maven-central/v/com.clivern/kafka-sdk.svg">
        </a>
		<a href="https://github.com/Clivern/kafka-sdk/blob/main/LICENSE">
            <img src="https://img.shields.io/badge/LICENSE-Apache_2.0-orange.svg">
        </a>
	</p>
</p>


## Documentation

### Installation

To add a dependency using Maven, use the following:

```xml
<dependency>
    <groupId>com.clivern</groupId>
    <artifactId>kafka-sdk</artifactId>
    <version>0.1.0</version>
</dependency>
```

To add a dependency using Gradle, use the following:

```java
dependencies {
    compile 'com.clivern:kafka-sdk:0.1.0'
}
```

To add a dependency using Scala SBT, use the following:

```java
libraryDependencies += "com.clivern" % "kafka-sdk" % "0.1.0"
```

### Usage

To Create a Kafka Topic:

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
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;


Configs configs = new Configs();
configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
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


## Versioning

For transparency into our release cycle and in striving to maintain backward compatibility, Kafka-sdk is maintained under the [Semantic Versioning guidelines](https://semver.org/) and release process is predictable and business-friendly.

See the [Releases section of our GitHub project](https://github.com/clivern/kafka-sdk/releases) for changelogs for each release version of Kafka-sdk. It contains summaries of the most noteworthy changes made in each release.


## Bug tracker

If you have any suggestions, bug reports, or annoyances please report them to our issue tracker at https://github.com/clivern/kafka-sdk/issues


## Security Issues

If you discover a security vulnerability within Kafka-sdk, please send an email to [hello@clivern.com](mailto:hello@clivern.com)


## Contributing

We are an open source, community-driven project so please feel free to join us. see the [contributing guidelines](CONTRIBUTING.md) for more details.


## License

© 2021, Clivern. Released under [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).

**Kafka-sdk** is authored and maintained by [@Clivern](http://github.com/clivern).
