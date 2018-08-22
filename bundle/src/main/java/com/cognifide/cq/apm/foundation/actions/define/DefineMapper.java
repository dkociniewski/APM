/*-
 * ========================LICENSE_START=================================
 * AEM Permission Management
 * %%
 * Copyright (C) 2013 Cognifide Limited
 * %%
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
 * =========================LICENSE_END==================================
 */
package com.cognifide.cq.apm.foundation.actions.define;

import com.cognifide.cq.apm.api.actions.Action;
import com.cognifide.cq.apm.api.actions.BasicActionMapper;
import com.cognifide.cq.apm.api.actions.annotations.Mapping;

public final class DefineMapper extends BasicActionMapper {

	public static final String REFERENCE = "Create definitions holding values used multiple times across CQSM script.\n"
			+ "To access definition value ${definition_name} syntax can be used."
			+ " Value must be specified between single quotes e.g 'my value'.";

	@Mapping(
			value = {
					"DEFINE" + SPACE + STRING + SPACE + QUOTED,
					"DEFINE" + SPACE + STRING + SPACE + STRING
			},
			args = {"name", "value"},
			reference = REFERENCE
	)
	public Action mapAction(final String name, final String value) {
		return new Define(name, value, false);
	}

	@Mapping(
			value = {
					"DEFINE" + SPACE + STRING + SPACE + QUOTED + SPACE + ("IF" + DASH + "NOT" + DASH + "EXISTS"),
					"DEFINE" + SPACE + STRING + SPACE + STRING + SPACE + ("IF" + DASH + "NOT" + DASH + "EXISTS")
			},
			args = {"name", "value"},
			reference = REFERENCE
	)
	public Action mapActionWithIfNotExists(final String name, final String value) {
		return new Define(name, value, true);
	}
}