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

import com.clivern.kafka.exception.MissingHandler;
import java.time.Duration;
import java.util.Collections;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/** Consumer Class */
public class Consumer {

    private Configs configs;

    private KafkaConsumer<String, String> consumer;

    private HandlerCallbackInterface handlerCallback;

    private SuccessCallbackInterface onSuccessCallback;

    private FailureCallbackInterface onFailureCallback;

    /**
     * Class Constructor
     *
     * @param configs a config instance
     */
    public Consumer(Configs configs) {
        this.configs = configs;
        this.consumer = new KafkaConsumer<>(this.configs.getProperties());
    }

    /**
     * Message Handler
     *
     * @param callback
     * @return the instance
     */
    public Consumer handler(HandlerCallbackInterface callback) {
        this.handlerCallback = callback;

        return this;
    }

    /**
     * Subscribe to a Topic
     *
     * @param topic the kafka topic
     * @return the instance
     */
    public Consumer subscribe(String topic) {
        this.consumer.subscribe(Collections.singleton(topic));

        return this;
    }

    /**
     * On Success Callback
     *
     * @param callback the callback
     * @return the instance
     */
    public Consumer onSuccess(SuccessCallbackInterface callback) {
        this.onSuccessCallback = callback;

        return this;
    }

    /**
     * On Failure Callback
     *
     * @param callback the callback
     * @return the instance
     */
    public Consumer onFailure(FailureCallbackInterface callback) {
        this.onFailureCallback = callback;

        return this;
    }

    /**
     * Run the daemon
     *
     * @throws MissingHandler if handler is missing
     */
    public void run() throws MissingHandler {
        if (this.handlerCallback == null) {
            throw new MissingHandler("Error! HandlerCallback is missing");
        }

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                try {
                    this.handlerCallback.trigger(record);

                    if (this.onSuccessCallback != null) {
                        this.onSuccessCallback.trigger(record);
                    }
                } catch (Exception e) {
                    if (this.onFailureCallback != null) {
                        this.onFailureCallback.trigger(record, e);
                    }
                }
            }

            consumer.commitAsync();
        }
    }
}
