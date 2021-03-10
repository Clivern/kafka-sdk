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
package com.clivern.asynq;

/** Asynq Class */
public class Asynq {

    /**
     * Get a New Producer Instance
     *
     * @param configs a config instance
     * @return producer instance
     */
    public Producer newProducer(Configs configs) {
        return new Producer(configs);
    }

    /**
     * Get a New Consumer Instance
     *
     * @param configs a config instance
     * @return consumer instance
     */
    public Consumer newConsumer(Configs configs) {
        return new Consumer(configs);
    }
}
