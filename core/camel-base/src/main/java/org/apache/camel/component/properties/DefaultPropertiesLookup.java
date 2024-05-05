/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.properties;

import lombok.SneakyThrows;
import org.apache.camel.NoTypeConversionAvailableException;
import org.apache.camel.PropertiesLookupListener;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.LoadablePropertiesSource;
import org.apache.camel.spi.PropertiesSource;
import org.apache.camel.util.OrderedLocationProperties;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * Default {@link PropertiesLookup} which lookup properties from a {@link java.util.Properties} with all existing
 * properties.
 */
public class DefaultPropertiesLookup implements PropertiesLookup {

    private final PropertiesComponent component;

    public DefaultPropertiesLookup(PropertiesComponent component) {
        this.component = component;
    }

    @Override
    public String lookup(String name, String defaultValue) {
        try {
            return doLookup(name, defaultValue);
        } catch (NoTypeConversionAvailableException e) {
            throw RuntimeCamelException.wrapRuntimeCamelException(e);
        }
    }

    private String doLookup(String name, String defaultValue) throws NoTypeConversionAvailableException {
        // local takes precedence
        Properties local = component.getLocalProperties();
        return findInLocalProperties(local, name)
                .or(() -> findInOverrideProperties(component.getOverrideProperties(), local, name, defaultValue))
                .or(() -> tryToFindInPropertiesSources(component.getPropertiesSources(), name, defaultValue))
                .or(() -> findInInitialProperties(component.getInitialProperties(), local, name, defaultValue))
                .orElse(null);
    }

    @SneakyThrows(NoTypeConversionAvailableException.class)
    private Optional<String> findInInitialProperties(Properties initialProperties, Properties local, String name, String defaultValue) {
        // use get as the value can potentially be stored as a non string value
        if (initialProperties != null) {
            String answer = null;
            Object value = component.getInitialProperties().get(name);
            if (value != null) {
                answer = component.getCamelContext().getTypeConverter().mandatoryConvertTo(String.class, value);
                String loc = location(local, name, "InitialProperties");
                onLookup(name, answer, defaultValue, loc);
            }
            return Optional.ofNullable(answer);
        }
        return Optional.empty();
    }

    @SneakyThrows(NoTypeConversionAvailableException.class)
    private Optional<String> findInOverrideProperties(Properties overrideProperties, Properties local, String name, String defaultValue) {
        String answer = null;
        if (overrideProperties != null) {
            // use get as the value can potentially be stored as a non string value
            Object value = overrideProperties.get(name);
            if (value != null) {
                answer = component.getCamelContext().getTypeConverter().mandatoryConvertTo(String.class, value);
                String loc = location(local, name, "OverrideProperties");
                onLookup(name, answer, defaultValue, loc);
            }
        }
        return Optional.ofNullable(answer);
    }

    private Optional<String> findInLocalProperties(Properties local, String name) throws NoTypeConversionAvailableException {
        String answer = null;
        if (local != null) {
            // use get as the value can potentially be stored as a non string value
            Object value = local.get(name);
            if (value != null) {
                answer = component.getCamelContext().getTypeConverter().mandatoryConvertTo(String.class, value);
                String loc = location(local, name, "LocalProperties");
                String localDefaultValue = getLocalDefaultValue(local, name);
                onLookup(name, answer, localDefaultValue, loc);
            }
        }
        return Optional.ofNullable(answer);
    }

    private String getLocalDefaultValue(Properties local, String name) throws NoTypeConversionAvailableException {
        String localDefaultValue = null;
        if (local instanceof OrderedLocationProperties) {
            Object val = ((OrderedLocationProperties) local).getDefaultValue(name);
            if (val != null) {
                localDefaultValue
                        = component.getCamelContext().getTypeConverter().mandatoryConvertTo(String.class, val);
            }
        }
        return localDefaultValue;
    }

    private Optional<String> tryToFindInPropertiesSources(List<PropertiesSource> propertiesSources, String name, String defaultValue) {
        String answer;
        // try till first found source
        for (PropertiesSource ps : propertiesSources) {
            answer = ps.getProperty(name, defaultValue);
            if (answer != null) {
                String source = getSource(ps, name);
                onLookup(name, answer, defaultValue, source);
                return Optional.of(answer);
            }
        }
        return Optional.empty();
    }

    private String getSource(PropertiesSource ps, String name) {
        String source = ps.getName();
        if (ps instanceof ClasspathPropertiesSource) {
            source = "classpath:" + ((LocationPropertiesSource) ps).getLocation().getPath();
        } else if (ps instanceof FilePropertiesSource) {
            source = "file:" + ((LocationPropertiesSource) ps).getLocation().getPath();
        } else if (ps instanceof RefPropertiesSource) {
            source = "ref:" + ((LocationPropertiesSource) ps).getLocation().getPath();
        } else if (ps instanceof LocationPropertiesSource) {
            source = ((LocationPropertiesSource) ps).getLocation().getPath();
        } else if (ps instanceof LoadablePropertiesSource) {
            Properties prop = ((LoadablePropertiesSource) ps).loadProperties();
            if (prop instanceof OrderedLocationProperties olp) {
                source = olp.getLocation(name);
            }
        }
        return source;
    }

    private void onLookup(String name, String value, String defaultValue, String source) {
        for (PropertiesLookupListener listener : component.getPropertiesLookupListeners()) {
            try {
                listener.onLookup(name, value, defaultValue, source);
            } catch (Exception e) {
                // ignore
            }
        }
    }

    private static String location(Properties prop, String name, String defaultLocation) {
        String loc = null;
        if (prop instanceof OrderedLocationProperties) {
            loc = ((OrderedLocationProperties) prop).getLocation(name);
        }
        if (loc == null) {
            loc = defaultLocation;
        }
        return loc;
    }

}
