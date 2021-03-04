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

import static org.junit.Assert.*;

import org.junit.Test;

/** Publisher Test Cases */
public class PublisherTest {

    private int count = 1;

    @Test
    public void testAttach() {
        MessagePublisher messagePublisher = new MessagePublisher();

        Observer callback1 =
                (message) -> {
                    this.count += 1;
                };

        Observer callback2 =
                (message) -> {
                    this.count += 1;
                };

        Observer callback3 =
                (message) -> {
                    this.count += 1;
                };

        messagePublisher.attach("x.event", callback1);
        messagePublisher.attach("x.event", callback2);
        messagePublisher.attach("y.event", callback3);
        messagePublisher.notify("x.event", new Message("Hello"));
        assertEquals(this.count, 3);

        messagePublisher.notify("y.event", new Message("Hello"));
        assertEquals(this.count, 4);
    }
}
