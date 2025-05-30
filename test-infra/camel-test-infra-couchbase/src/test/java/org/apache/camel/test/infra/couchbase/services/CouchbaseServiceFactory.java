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

package org.apache.camel.test.infra.couchbase.services;

import org.apache.camel.test.infra.common.services.SimpleTestServiceBuilder;
import org.apache.camel.test.infra.common.services.SingletonService;

public final class CouchbaseServiceFactory {
    static class SingletonCouchbaseService extends SingletonService<CouchbaseService> implements CouchbaseService {
        public SingletonCouchbaseService(CouchbaseService service, String name) {
            super(service, name);
        }

        @Override
        public String getConnectionString() {
            return getService().getConnectionString();
        }

        @Override
        public String getUsername() {
            return getService().getUsername();
        }

        @Override
        public String getPassword() {
            return getService().getPassword();
        }

        @Override
        public String getHostname() {
            return getService().getHostname();
        }

        @Override
        public int getPort() {
            return getService().getPort();
        }

        @Override
        public String protocol() {
            return getService().protocol();
        }

        @Override
        public String hostname() {
            return getService().hostname();
        }

        @Override
        public int port() {
            return getService().port();
        }

        @Override
        public String username() {
            return getService().username();
        }

        @Override
        public String password() {
            return getService().password();
        }

        @Override
        public String bucket() {
            return getService().bucket();
        }

        @Override
        public String viewName() {
            return getService().viewName();
        }

        @Override
        public String designDocumentName() {
            return getService().designDocumentName();
        }
    }

    private CouchbaseServiceFactory() {

    }

    public static SimpleTestServiceBuilder<CouchbaseService> builder() {
        return new SimpleTestServiceBuilder<>("couchbase");
    }

    public static CouchbaseService createService() {
        return builder()
                .addLocalMapping(CouchbaseLocalContainerTestService::new)
                .addRemoteMapping(CouchbaseRemoteTestService::new)
                .build();
    }

    public static CouchbaseService createSingletonService() {
        return SingletonServiceHolder.INSTANCE;
    }

    @Deprecated
    public static CouchbaseService getService() {
        return createService();
    }

    private static class SingletonServiceHolder {
        static final CouchbaseService INSTANCE;
        static {
            SimpleTestServiceBuilder<CouchbaseService> instance = builder();

            instance.addLocalMapping(() -> new SingletonCouchbaseService(new CouchbaseLocalContainerTestService(), "couchbase"))
                    .addRemoteMapping(CouchbaseRemoteTestService::new);

            INSTANCE = instance.build();
        }
    }

    public static class CouchbaseLocalContainerTestService extends CouchbaseLocalContainerInfraService
            implements CouchbaseService {
    }

    public static class CouchbaseRemoteTestService extends CouchbaseRemoteInfraService implements CouchbaseService {
    }
}
