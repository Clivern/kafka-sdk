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

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/** Configs Class */
public class Configs {

    private Properties properties;

    /** Class Constructor */
    public Configs() {
        this.properties = new Properties();
    }

    /**
     * Class Constructor
     *
     * @param defaults the defaults
     */
    public Configs(Properties defaults) {
        this.properties = defaults;
    }

    /**
     * Get a property value
     *
     * @param key the property key
     * @param defaultValue the property default value
     * @return the value
     */
    public String get(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    /**
     * Get a property value
     *
     * @param key the property key
     * @return the value
     */
    public String get(String key) {
        return this.properties.getProperty(key);
    }

    /**
     * Set a property value
     *
     * @param key the property key
     * @param value the property value
     */
    public void set(String key, String value) {
        this.properties.setProperty(key, value);
    }

    /**
     * Get Keys
     *
     * @return the keys set
     */
    public Set<String> getKeys() {
        return this.properties.stringPropertyNames();
    }

    /**
     * Load from a properties file
     *
     * @param filePath the file path
     * @return Configs
     * @throws IOException throws exception if file is missing
     */
    public static Configs fromFile(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        Properties p = new Properties();
        p.load(reader);

        return new Configs(p);
    }
}
