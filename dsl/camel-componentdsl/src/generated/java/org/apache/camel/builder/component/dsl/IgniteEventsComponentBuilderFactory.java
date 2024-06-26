/* Generated by camel build tools - do NOT edit this file! */
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
package org.apache.camel.builder.component.dsl;

import javax.annotation.processing.Generated;
import org.apache.camel.Component;
import org.apache.camel.builder.component.AbstractComponentBuilder;
import org.apache.camel.builder.component.ComponentBuilder;
import org.apache.camel.component.ignite.events.IgniteEventsComponent;

/**
 * Receive events from an Ignite cluster by creating a local event listener.
 * 
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.ComponentDslMojo")
public interface IgniteEventsComponentBuilderFactory {

    /**
     * Ignite Events (camel-ignite)
     * Receive events from an Ignite cluster by creating a local event listener.
     * 
     * Category: messaging,cache,clustering
     * Since: 2.17
     * Maven coordinates: org.apache.camel:camel-ignite
     * 
     * @return the dsl builder
     */
    static IgniteEventsComponentBuilder igniteEvents() {
        return new IgniteEventsComponentBuilderImpl();
    }

    /**
     * Builder for the Ignite Events component.
     */
    interface IgniteEventsComponentBuilder extends ComponentBuilder<IgniteEventsComponent> {
    
        
        /**
         * Allows for bridging the consumer to the Camel routing Error Handler,
         * which mean any exceptions (if possible) occurred while the Camel
         * consumer is trying to pickup incoming messages, or the likes, will
         * now be processed as a message and handled by the routing Error
         * Handler. Important: This is only possible if the 3rd party component
         * allows Camel to be alerted if an exception was thrown. Some
         * components handle this internally only, and therefore
         * bridgeErrorHandler is not possible. In other situations we may
         * improve the Camel component to hook into the 3rd party component and
         * make this possible for future releases. By default the consumer will
         * use the org.apache.camel.spi.ExceptionHandler to deal with
         * exceptions, that will be logged at WARN or ERROR level and ignored.
         * 
         * The option is a: &lt;code&gt;boolean&lt;/code&gt; type.
         * 
         * Default: false
         * Group: consumer
         * 
         * @param bridgeErrorHandler the value to set
         * @return the dsl builder
         */
        default IgniteEventsComponentBuilder bridgeErrorHandler(boolean bridgeErrorHandler) {
            doSetProperty("bridgeErrorHandler", bridgeErrorHandler);
            return this;
        }
    
        /**
         * The resource from where to load the configuration. It can be a: URL,
         * String or InputStream type.
         * 
         * The option is a: &lt;code&gt;java.lang.Object&lt;/code&gt; type.
         * 
         * Group: consumer
         * 
         * @param configurationResource the value to set
         * @return the dsl builder
         */
        default IgniteEventsComponentBuilder configurationResource(java.lang.Object configurationResource) {
            doSetProperty("configurationResource", configurationResource);
            return this;
        }
    
        /**
         * To use an existing Ignite instance.
         * 
         * The option is a: &lt;code&gt;org.apache.ignite.Ignite&lt;/code&gt;
         * type.
         * 
         * Group: consumer
         * 
         * @param ignite the value to set
         * @return the dsl builder
         */
        default IgniteEventsComponentBuilder ignite(org.apache.ignite.Ignite ignite) {
            doSetProperty("ignite", ignite);
            return this;
        }
    
        /**
         * Allows the user to set a programmatic ignite configuration.
         * 
         * The option is a:
         * &lt;code&gt;org.apache.ignite.configuration.IgniteConfiguration&lt;/code&gt; type.
         * 
         * Group: consumer
         * 
         * @param igniteConfiguration the value to set
         * @return the dsl builder
         */
        default IgniteEventsComponentBuilder igniteConfiguration(org.apache.ignite.configuration.IgniteConfiguration igniteConfiguration) {
            doSetProperty("igniteConfiguration", igniteConfiguration);
            return this;
        }
    
        
        /**
         * Whether autowiring is enabled. This is used for automatic autowiring
         * options (the option must be marked as autowired) by looking up in the
         * registry to find if there is a single instance of matching type,
         * which then gets configured on the component. This can be used for
         * automatic configuring JDBC data sources, JMS connection factories,
         * AWS Clients, etc.
         * 
         * The option is a: &lt;code&gt;boolean&lt;/code&gt; type.
         * 
         * Default: true
         * Group: advanced
         * 
         * @param autowiredEnabled the value to set
         * @return the dsl builder
         */
        default IgniteEventsComponentBuilder autowiredEnabled(boolean autowiredEnabled) {
            doSetProperty("autowiredEnabled", autowiredEnabled);
            return this;
        }
    }

    class IgniteEventsComponentBuilderImpl
            extends AbstractComponentBuilder<IgniteEventsComponent>
            implements IgniteEventsComponentBuilder {
        @Override
        protected IgniteEventsComponent buildConcreteComponent() {
            return new IgniteEventsComponent();
        }
        @Override
        protected boolean setPropertyOnComponent(
                Component component,
                String name,
                Object value) {
            switch (name) {
            case "bridgeErrorHandler": ((IgniteEventsComponent) component).setBridgeErrorHandler((boolean) value); return true;
            case "configurationResource": ((IgniteEventsComponent) component).setConfigurationResource((java.lang.Object) value); return true;
            case "ignite": ((IgniteEventsComponent) component).setIgnite((org.apache.ignite.Ignite) value); return true;
            case "igniteConfiguration": ((IgniteEventsComponent) component).setIgniteConfiguration((org.apache.ignite.configuration.IgniteConfiguration) value); return true;
            case "autowiredEnabled": ((IgniteEventsComponent) component).setAutowiredEnabled((boolean) value); return true;
            default: return false;
            }
        }
    }
}