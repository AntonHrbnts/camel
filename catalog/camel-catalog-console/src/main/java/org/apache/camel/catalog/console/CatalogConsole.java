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
package org.apache.camel.catalog.console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.camel.catalog.CamelCatalog;
import org.apache.camel.catalog.DefaultCamelCatalog;
import org.apache.camel.spi.annotations.DevConsole;
import org.apache.camel.support.console.AbstractDevConsole;
import org.apache.camel.tooling.model.ArtifactModel;
import org.apache.camel.tooling.model.OtherModel;
import org.apache.camel.util.json.JsonObject;

@DevConsole(name = "catalog", description = "Information about used Camel artifacts")
public class CatalogConsole extends AbstractDevConsole {

    private static final String CP = System.getProperty("java.class.path");
    private final CamelCatalog catalog = new DefaultCamelCatalog(true);

    public CatalogConsole() {
        super("camel", "catalog", "Catalog", "Information about used Camel artifacts");
    }

    @Override
    protected String doCallText(Map<String, Object> options) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nComponents:\n");
        sb.append(toStringBuilder(modelStream(getCamelContext().getComponentNames(), catalog::componentModel)));

        sb.append("\n\nLanguages:\n");
        sb.append(toStringBuilder(modelStream(getCamelContext().getLanguageNames(), catalog::languageModel)));

        sb.append("\n\nData Formats:\n");
        sb.append(toStringBuilder(modelStream(getCamelContext().getDataFormatNames(), catalog::dataFormatModel)));

        // misc is harder to find as we need to find them via classpath
        sb.append("\n\nMiscellaneous Components:\n");
        evalMisc(sb, CatalogConsole::appendModel);

        return sb.toString();
    }

    @Override
    protected JsonObject doCallJson(Map<String, Object> options) {
        JsonObject root = new JsonObject();
        root.put("components", toJsonObjectList(modelStream(getCamelContext().getComponentNames(), catalog::componentModel)));
        root.put("dataformat", toJsonObjectList(modelStream(getCamelContext().getDataFormatNames(), catalog::dataFormatModel)));
        root.put("languages", toJsonObjectList(modelStream(getCamelContext().getLanguageNames(), catalog::languageModel)));

        // misc is harder to find as we need to find them via classpath
        root.put("others", extractOthers());
        return root;
    }

    private <T> void evalMisc(T consumable, BiConsumer<ArtifactModel<?>, T> consumer) {
        String[] cp = CP.split("[:|;]");
        String suffix = "-" + getCamelContext().getVersion() + ".jar";
        for (String c : cp) {
            if (c.endsWith(suffix)) {
                int pos = Math.max(c.lastIndexOf("/"), c.lastIndexOf("\\"));
                if (pos > 0) {
                    c = c.substring(pos + 1, c.length() - suffix.length());
                    consumer.accept(findOtherModel(c), consumable);
                }
            }
        }
    }

    private List<JsonObject> extractOthers() {
        List<JsonObject> others = new ArrayList<>();
        evalMisc(others, CatalogConsole::appendModel);
        return others;
    }

    private List<JsonObject> toJsonObjectList(Stream<ArtifactModel<?>> values) {
        return values.map(CatalogConsole::modelToJsonObject).toList();
    }

    private StringBuilder toStringBuilder(Stream<ArtifactModel<?>> values) {
        StringBuilder sb = new StringBuilder();
        values.map(CatalogConsole::modelToString).forEach(sb::append);
        return sb;
    }

    private Stream<ArtifactModel<?>> modelStream(Set<String> names, Function<String, ArtifactModel<?>> extractor){
        return names.stream().map(extractor).filter(Objects::nonNull);
    }

    private ArtifactModel<?> findOtherModel(String artifactId) {
        for (String name : catalog.findOtherNames()) {
            OtherModel model = catalog.otherModel(name);
            if (model != null && model.getArtifactId().equals(artifactId)) {
                return model;
            }
        }
        return null;
    }

    private static void appendModel(ArtifactModel<?> model, StringBuilder sb) {
        sb.append(modelToString(model));
    }

    private static String modelToString(ArtifactModel<?> model) {
        String level = model.getSupportLevel().toString();
        if (model.isDeprecated()) {
            level += "-deprecated";
        }
        return String.format("\n    %s %s %s %s: %s", model.getArtifactId(), level,
                model.getFirstVersionShort(), model.getTitle(), model.getDescription());
    }

    private static void appendModel(ArtifactModel<?> model, List<JsonObject> list) {
        if (model != null) {
            list.add(modelToJsonObject(model));
        }
    }

    private static JsonObject modelToJsonObject(ArtifactModel<?> model) {
        JsonObject jo = new JsonObject();
        String level = model.getSupportLevel().toString();
        if (model.isDeprecated()) {
            level += "-deprecated";
        }
        jo.put("groupId", model.getGroupId());
        jo.put("artifactId", model.getArtifactId());
        jo.put("version", model.getVersion());
        jo.put("level", level);
        jo.put("firstVersion", model.getFirstVersionShort());
        jo.put("title", model.getTitle());
        jo.put("description", model.getDescription());
        return jo;
    }
}
