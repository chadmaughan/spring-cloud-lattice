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

package org.springframework.cloud.lattice.connector;

import org.springframework.cloud.service.common.RedisServiceInfo;

import org.cloudfoundry.receptor.commands.ActualLRPResponse;

/**
 * @author Spencer Gibb
 */
public class RedisServiceInfoCreator extends LatticeServiceInfoCreator<RedisServiceInfo> {

	public RedisServiceInfoCreator() {
		super("redis");
	}

	@Override
	public RedisServiceInfo createServiceInfo(Process process) {
		ActualLRPResponse actual = process.getFirstActual();
		String address = actual.getAddress();
		int port = actual.getPorts()[0].getHostPort();
		String password = null;
		return new RedisServiceInfo(actual.getInstanceGuid(), address, port, password);
	}

}
