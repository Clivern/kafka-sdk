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

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import org.junit.Test;

/** Configs Test Cases */
public class ConfigsTest {

    @Test
    public void testConfigs() throws IOException {
        Configs configs = new Configs();
        configs.set("key4", "value4");
        assertEquals(configs.get("key4"), "value4");

        configs = Configs.fromFile("src/test/resources/config.properties");
        assertEquals(configs.get("key1"), "value1");
        assertEquals(configs.get("not_found", "missing"), "missing");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Key1", "Value1");
        map.put("Key2", "Value2");

        configs = Configs.fromMap(map);
        assertEquals(configs.get("Key1"), "Value1");
    }
}
