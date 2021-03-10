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

import java.util.Set;

/** Consumer Class */
public class Consumer {

    private Configs configs;

    /**
     * Class Constructor
     *
     * @param configs a config instance
     */
    public Consumer(Configs configs) {
        this.configs = configs;
    }

    public Consumer handler(CallbackInterface callback) {
        return this;
    }

    public Consumer subscribe(Set<String> topics) {
        return this;
    }

    public Consumer onSuccess(CallbackInterface callback) {
        return this;
    }

    public Consumer onFailure(CallbackInterface callback) {
        return this;
    }

    public Consumer unsubscribe() {
        return this;
    }
}
