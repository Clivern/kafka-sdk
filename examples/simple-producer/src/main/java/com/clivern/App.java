/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.clivern;

import com.clivern.kafka.Configs;
import com.clivern.kafka.Producer;
import com.clivern.kafka.Kafka;
import java.util.HashMap;
import com.clivern.kafka.Utils;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class App {
    public void producer() {
        Configs configs = new Configs();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.local:9092");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        Producer producer = (new Kafka()).newProducer(configs);

        for (int i = 0; i < 1000; i++) {
            ProducerRecord<String, String> record =
                    new ProducerRecord<>("clivern", null, "Hello World " + i);

            producer.send(record).flush();
        }

        producer.close();
    }

    public static void main(String[] args) {
        new App().ensureTopic("clivern");
        new App().producer();
    }

    public void ensureTopic(String name) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("bootstrap.servers", "kafka.local:9092");
        Utils.createTopic("clivern", Configs.fromMap(map));
    }
}