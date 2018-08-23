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
package com.cognifide.cq.cqsm.core.actions.executor;

import com.cognifide.cq.cqsm.api.actions.Action;
import com.cognifide.cq.cqsm.api.actions.ActionDescriptor;
import com.cognifide.cq.cqsm.api.actions.ActionResult;
import com.cognifide.cq.cqsm.api.exceptions.ActionException;
import com.cognifide.cq.cqsm.api.executors.Context;

public final class RunActionExecutor extends AbstractActionExecutor {

  public RunActionExecutor(Context context) {
    super(context);
	}

	@Override
	public ActionResult execute(ActionDescriptor actionDescriptor) {
		try {
			final Action action = createAction(actionDescriptor);

			return action.execute(context);
		} catch (ActionException e) {
			ActionResult actionResult = new ActionResult();
			actionResult.logError(e.getMessage());

			return actionResult;
		}
	}

}