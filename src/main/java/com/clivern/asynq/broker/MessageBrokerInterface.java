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
package com.clivern.asynq.broker;

import java.util.HashMap;

/** MessageBrokerInterface Interface */
public interface MessageBrokerInterface {

    /**
     * Configure the broker
     *
     * @param configs a hashmap of configs
     */
    public void configure(HashMap<String, String> configs);

    /**
     * Connect to the broker
     *
     * @return whether it connected or not
     */
    public Boolean connect();

    /**
     * Disconnect from the broker
     *
     * @return whether it disconnected or not
     */
    public Boolean disconnect();

    /**
     * Reconnect to the broker
     *
     * @return whether it reconnected or not
     */
    public Boolean reconnect();

    /**
     * Consume the Queue
     *
     * @param consumer the consumer instance
     */
    public void consume(ConsumerInterface consumer);

    /**
     * Send a Message
     *
     * @param message the message
     * @return whether sending succeeded or not
     */
    public Boolean send(MessageInterface message);
}
