/*
 * Copyright 2013-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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

package org.springframework.cloud.lattice.discovery;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileEnvironmentPostProcessor;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

/**
 * @author Spencer Gibb
 */
public class LatticeEnvironmentPostProcessor implements
		EnvironmentPostProcessor, Ordered {

    // Before ConfigFileApplicationListener
    private int order = ConfigFileEnvironmentPostProcessor.DEFAULT_ORDER - 1;

    @Override
    public int getOrder() {
        return order;
    }

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Object processGuid = System.getenv("PROCESS_GUID");
        if (processGuid != null) {
            MapPropertySource propertySource = new MapPropertySource(
                    "latticeBridgeEnvironment", Collections.singletonMap(
                    "spring.application.name", processGuid));
            environment.getPropertySources().addLast(propertySource);
        }
    }
}
