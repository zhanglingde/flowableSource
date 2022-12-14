/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.camel.examples.ping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.flowable.engine.test.Deployment;
import org.flowable.spring.impl.test.SpringFlowableTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Saeid Mirzaei
 */
@Tag("camel")
@ContextConfiguration("classpath:generic-camel-flowable-context.xml")
public class PingPongTest extends SpringFlowableTestCase {

    @Autowired
    protected CamelContext camelContext;

    @BeforeEach
    public void setUp() throws Exception {
        camelContext.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("flowable:PingPongProcess:ping").transform().simple("${property.input} World");
            }
        });
    }

    @Test
    @Deployment
    public void testPingPong() {
        Map<String, Object> variables = new HashMap<>();

        variables.put("input", "Hello");
        Map<String, String> outputMap = new HashMap<>();
        variables.put("outputMap", outputMap);

        runtimeService.startProcessInstanceByKey("PingPongProcess", variables);
        assertThat(outputMap)
                .containsExactly(entry("outputValue", "Hello World"));
    }

}
