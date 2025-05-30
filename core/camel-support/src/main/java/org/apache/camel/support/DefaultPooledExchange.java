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
package org.apache.camel.support;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.PooledExchange;
import org.apache.camel.clock.Clock;

/**
 * The default and only implementation of {@link PooledExchange}.
 */
public final class DefaultPooledExchange extends AbstractExchange implements PooledExchange {
    private final ResetableClock clock;
    private OnDoneTask onDone;
    private Class<?> originalInClassType;
    private Message originalOut;
    private final ExchangePattern originalPattern;
    private boolean autoRelease;

    public DefaultPooledExchange(CamelContext context) {
        super(context);
        this.originalPattern = getPattern();
        this.properties = new ConcurrentHashMap<>(8);
        this.clock = new ResetableClock();
    }

    public DefaultPooledExchange(Exchange parent) {
        super(parent);
        this.originalPattern = parent.getPattern();
        this.properties = new ConcurrentHashMap<>(8);

        Clock parentClock = parent.getClock();

        if (parentClock instanceof ResetableClock rs) {
            this.clock = rs;
        } else {
            this.clock = new ResetableClock(parent.getClock());
        }
    }

    public DefaultPooledExchange(CamelContext context, ExchangePattern pattern) {
        super(context, pattern);
        this.originalPattern = getPattern();
        this.properties = new ConcurrentHashMap<>(8);

        this.clock = new ResetableClock();
    }

    @Override
    AbstractExchange newCopy() {
        // NOTE: this is the same behavior as done previously from AbstractExchange when returning a copy.
        return new DefaultExchange(this);
    }

    public boolean isAutoRelease() {
        return autoRelease;
    }

    public void setAutoRelease(boolean autoRelease) {
        this.autoRelease = autoRelease;
    }

    @Override
    public void onDone(OnDoneTask task) {
        this.onDone = task;
    }

    public void done() {
        if (clock.getCreated() > 0) {
            // by unsetting (setting to 0) we also flag that this exchange is done and needs to be reset to use again
            clock.unset();

            this.properties.clear();
            internalProperties.clear();
            if (this.safeCopyProperties != null) {
                this.safeCopyProperties.clear();
            }
            this.exchangeId = null;
            if (in != null && in.getClass() == originalInClassType) {
                // okay we can reuse in
                in.reset();
            } else {
                this.in = null;
            }
            if (out != null) {
                out.reset();
                this.out = null;
            }
            this.exception = null;
            // reset pattern to original
            this.pattern = originalPattern;
            // do not reset endpoint/fromRouteId as it would be the same consumer/endpoint again

            getExchangeExtension().reset();

            if (onDone != null) {
                onDone.onDone(this);
            }
        }
    }

    @Override
    @Deprecated(since = "4.4.0")
    public void reset(long created) {
        clock.reset();
    }

    @Override
    public Message getIn() {
        if (in == null) {
            in = new DefaultMessage(getContext());
            originalInClassType = in.getClass();
            configureMessage(in);
        }
        return in;
    }

    @Override
    public void setIn(Message in) {
        this.in = in;
        configureMessage(in);
        if (in != null) {
            this.originalInClassType = in.getClass();
        }
    }

    @Override
    public Message getOut() {
        // lazy create
        if (out == null) {
            if (originalOut != null) {
                out = originalOut;
            } else {
                // we can only optimize OUT when its using a default message instance
                out = new DefaultMessage(this);
                configureMessage(out);
                originalOut = out;
            }
        }
        return out;
    }

    @Override
    public void setOut(Message out) {
        this.out = out;
        if (out != null) {
            configureMessage(out);
            this.originalOut = null; // we use custom out
        }
    }

    @Override
    public Clock getClock() {
        return clock;
    }

    public static DefaultPooledExchange newFromEndpoint(Endpoint fromEndpoint) {
        return newFromEndpoint(fromEndpoint, fromEndpoint.getExchangePattern());
    }

    public static DefaultPooledExchange newFromEndpoint(Endpoint fromEndpoint, ExchangePattern exchangePattern) {
        DefaultPooledExchange exchange = new DefaultPooledExchange(fromEndpoint.getCamelContext(), exchangePattern);
        exchange.getExchangeExtension().setFromEndpoint(fromEndpoint);
        return exchange;
    }
}
