/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.clivern;

import com.clivern.kafka.Configs;
import com.clivern.kafka.Consumer;
import com.clivern.kafka.Kafka;
import com.clivern.kafka.exception.MissingHandler;
import com.clivern.kafka.HandlerCallbackInterface;
import com.clivern.kafka.FailureCallbackInterface;
import com.clivern.kafka.SuccessCallbackInterface;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class App {
    public void consumer() throws MissingHandler {
        Configs configs = new Configs();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.local:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "clivern");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        Consumer consumer = (new Kafka()).newConsumer(configs);

        HandlerCallbackInterface<ConsumerRecord<String, String>> handler =
                (record) -> {
                    System.out.println("Message Received: " + record.value());

                    // Throw error if message has error
                    if (record.value().equals("error")) {
                        throw new Exception("Error!");
                    }
                };

        SuccessCallbackInterface<ConsumerRecord<String, String>> onSuccess =
                (record) -> {
                    System.out.println("Message Succeeded: " + record.value());
                };

        FailureCallbackInterface<ConsumerRecord<String, String>> onFailure =
                (record, exception) -> {
                    System.out.println(
                            "Message " + record.value() + " Failed: " + exception.getMessage());
                };

        consumer.subscribe("clivern")
                .handler(handler)
                .onSuccess(onSuccess)
                .onFailure(onFailure)
                .run();
    }

    public static void main(String[] args) throws MissingHandler {
        new App().consumer();
    }
}
