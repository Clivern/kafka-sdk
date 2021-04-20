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
package com.clivern.asynq.event;

import java.util.HashMap;
import java.util.List;

/** Message Publisher Class */
public class MessagePublisher implements SubjectInterface {

    private HashMap<String, List<Observer>> observers = new HashMap<>();

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(String event, Observer observer) {
        this.observers.get(event).remove(observer);
    }

    @Override
    public void notifyUpdate(Message m) {
        for (Observer observer : observers) {
            observer.update(m);
        }
    }
}
