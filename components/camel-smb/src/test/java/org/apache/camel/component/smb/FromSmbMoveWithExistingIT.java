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
package org.apache.camel.component.smb;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromSmbMoveWithExistingIT extends SmbServerTestSupport {

    @Override
    public void doPostSetup() throws Exception {
        template.sendBodyAndHeader(getSmbUrl(), "Hello World this file will be moved", Exchange.FILE_NAME, "hello.txt");
    }

    protected String getSmbUrl() {
        return String.format(
                "smb:%s/%s/moveexist?username=%s&password=%s&move=${file:name}.old",
                service.address(), service.shareName(), service.userName(), service.password());
    }

    @Test
    public void testRemoveExistingMoveFile() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived("Hello World this file will be moved");

        mock.assertIsSatisfied();

        // verify file resides in the smb dir within the docker container
        await().atMost(3, TimeUnit.SECONDS)
                .untilAsserted(() -> assertEquals("Hello World this file will be moved",
                        new String(copyFileContentFromContainer("/data/rw/moveexist/hello.txt.old"))));
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from(getSmbUrl())
                        .to("mock:result");
            }
        };
    }
}
