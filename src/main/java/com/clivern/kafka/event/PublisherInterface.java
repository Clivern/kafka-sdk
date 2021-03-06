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
package com.clivern.kafka.event;

import com.clivern.kafka.exception.MissingEvent;

/** PublisherInterface interface */
public interface PublisherInterface {

    /**
     * Attach observer to an event
     *
     * @param event the event
     * @param observer the new observer
     */
    public void attach(String event, Observer observer);

    /**
     * Notify event observers
     *
     * @param event the event
     * @param message the message
     * @throws MissingEvent throws exception if event is missing
     */
    public void notify(String event, Message message) throws MissingEvent;
}
