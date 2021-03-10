/*
 * Copyright (C) 2021 Clivern <http://clivern.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.clivern.kafka;

import java.time.Duration;
import java.util.Collections;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/** Consumer Class */
public class Consumer {

    private Configs configs;

    private KafkaConsumer<String, String> consumer;

    private CallbackInterface handlerCallback;

    private CallbackInterface onSuccessCallback;

    private CallbackInterface onFailureCallback;

    /**
     * Class Constructor
     *
     * @param configs a config instance
     */
    public Consumer(Configs configs) {
        this.configs = configs;
        this.consumer = new KafkaConsumer<>(this.configs.getProperties());
    }

    public Consumer handler(CallbackInterface callback) {
        this.handlerCallback = callback;

        return this;
    }

    public Consumer subscribe(String topic) {
        this.consumer.subscribe(Collections.singleton(topic));

        return this;
    }

    public Consumer onSuccess(CallbackInterface callback) {
        this.onSuccessCallback = callback;

        return this;
    }

    public Consumer onFailure(CallbackInterface callback) {
        this.onFailureCallback = callback;

        return this;
    }

    public void run() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                this.handlerCallback.trigger(record);
            }

            consumer.commitAsync();
        }
    }
}
