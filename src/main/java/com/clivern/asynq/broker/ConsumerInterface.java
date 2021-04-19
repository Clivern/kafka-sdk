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

/** ConsumerInterface Interface */
public interface ConsumerInterface {

    /**
     * Execute the consumer
     *
     * @param message the message
     */
    public void execute(Message message);

    /**
     * Post Execute
     *
     * @param broker the message broker
     */
    public void postExecute(MessageBrokerInterface broker);

    /** On Error */
    public void onError();

    /** On Success */
    public void onSuccess();
}
