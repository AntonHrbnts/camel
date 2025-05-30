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
package org.apache.camel.dsl.yaml.deserializers;

import java.util.List;

import org.apache.camel.dsl.yaml.common.YamlDeserializerBase;
import org.apache.camel.model.BeanFactoryDefinition;
import org.apache.camel.model.TemplatedRouteDefinition;
import org.apache.camel.model.TemplatedRouteParameterDefinition;
import org.apache.camel.spi.annotations.YamlIn;
import org.apache.camel.spi.annotations.YamlProperty;
import org.apache.camel.spi.annotations.YamlType;
import org.snakeyaml.engine.v2.nodes.Node;

@YamlIn
@YamlType(
          nodes = { "templatedRoute" },
          types = TemplatedRouteDefinition.class,
          order = org.apache.camel.dsl.yaml.common.YamlDeserializerResolver.ORDER_LOWEST - 1,
          properties = {
                  @YamlProperty(name = "routeId",
                                type = "string"),
                  @YamlProperty(name = "prefixId",
                                type = "string"),
                  @YamlProperty(name = "routeTemplateRef",
                                type = "string",
                                required = true),
                  @YamlProperty(name = "parameters",
                                type = "array:org.apache.camel.model.TemplatedRouteParameterDefinition"),
                  @YamlProperty(name = "beans",
                                type = "array:org.apache.camel.model.BeanFactoryDefinition")
          })
public class TemplatedRouteDefinitionDeserializer extends YamlDeserializerBase<TemplatedRouteDefinition> {

    public TemplatedRouteDefinitionDeserializer() {
        super(TemplatedRouteDefinition.class);
    }

    @Override
    protected TemplatedRouteDefinition newInstance() {
        return new TemplatedRouteDefinition();
    }

    @Override
    protected boolean setProperty(
            TemplatedRouteDefinition target, String propertyKey, String propertyName, Node node) {

        propertyKey = org.apache.camel.util.StringHelper.dashToCamelCase(propertyKey);
        switch (propertyKey) {
            case "routeId": {
                target.setRouteId(asText(node));
                break;
            }
            case "prefixId": {
                target.setPrefixId(asText(node));
                break;
            }
            case "routeTemplateRef": {
                target.setRouteTemplateRef(asText(node));
                break;
            }
            case "parameters": {
                List<TemplatedRouteParameterDefinition> items = asFlatList(node, TemplatedRouteParameterDefinition.class);
                target.setParameters(items);
                break;
            }
            case "beans": {
                List<BeanFactoryDefinition<TemplatedRouteDefinition>> items
                        = (List) asFlatList(node, BeanFactoryDefinition.class);
                target.setBeans(items);
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
}
