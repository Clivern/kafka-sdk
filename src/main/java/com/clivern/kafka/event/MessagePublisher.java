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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Message Publisher Class */
public class MessagePublisher implements PublisherInterface {

    private HashMap<String, List<Observer>> observers = new HashMap<>();

    @Override
    public void attach(String event, Observer observer) {
        this.observers.putIfAbsent(event, new ArrayList<Observer>());
        this.observers.get(event).add(observer);
    }

    @Override
    public void notify(String event, Message message) throws MissingEvent {
        if (observers.get(event) == null) {
            throw new MissingEvent(String.format("Event %s is missing", event));
        }

        for (Observer observer : observers.get(event)) {
            observer.trigger(message);
        }
    }
}
