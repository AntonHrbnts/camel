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
package org.apache.camel.processor;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMockTwoRoutesTest extends ContextTestSupport {

    @Test
    public void testSimple() throws Exception {
        assertEquals(2, context.getRoutesSize());

        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedBodiesReceived("Hello World");

        template.sendBody("direct:start", "Hello World");

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testSimpleTwoMessages() throws Exception {
        assertEquals(2, context.getRoutesSize());

        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedBodiesReceived("Hello World", "Bye World");

        template.sendBody("direct:start", "Hello World");
        template.sendBody("direct:start", "Bye World");

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start").routeId("foo")
                        .to("log:foo")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) {
                                assertEquals("foo", exchange.getUnitOfWork().getRoute().getRouteId());
                            }
                        })
                        .to("mock:foo")
                        .to("direct:bar")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) {
                                assertEquals("foo", exchange.getUnitOfWork().getRoute().getRouteId());
                            }
                        })
                        .to("mock:result");

                from("direct:bar").routeId("bar")
                        .to("log:bar")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) {
                                assertEquals("bar", exchange.getUnitOfWork().getRoute().getRouteId());
                            }
                        })
                        .to("mock:bar");
            }
        };
    }
}
