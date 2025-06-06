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
package org.apache.camel.main.util;

import java.util.Arrays;
import java.util.List;

import org.apache.camel.catalog.suggest.CatalogSuggestionStrategy;

public final class SuggestSimilarHelper {

    private static final int MAX_SUGGESTIONS = 5;

    private SuggestSimilarHelper() {
    }

    public static List<String> didYouMean(List<String> names, String unknown) {
        String[] suggestions = CatalogSuggestionStrategy.suggestEndpointOptions(names, unknown, MAX_SUGGESTIONS);
        if (suggestions != null) {
            return Arrays.asList(suggestions);
        }
        return List.of();
    }

}
