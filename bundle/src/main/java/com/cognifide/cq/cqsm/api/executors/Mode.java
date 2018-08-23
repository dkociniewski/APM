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
package com.cognifide.cq.cqsm.api.executors;

import com.cognifide.cq.cqsm.core.actions.executor.ActionExecutor;
import com.cognifide.cq.cqsm.core.actions.executor.DryRunActionExecutor;
import com.cognifide.cq.cqsm.core.actions.executor.RunActionExecutor;
import com.cognifide.cq.cqsm.core.actions.executor.ValidationActionExecutor;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

public enum Mode implements Serializable {

	RUN {
		@Override
    public ActionExecutor getExecutor(Context context) {
      return new RunActionExecutor(context);
		}

		@Override
		public boolean isRun() {
			return true;
		}

		@Override
		public String getName() {
			return "run";
		}
	},
	AUTOMATIC_RUN {
		@Override
    public ActionExecutor getExecutor(Context context) {
      return RUN.getExecutor(context);
		}

		@Override
		public boolean isRun() {
			return RUN.isRun();
		}

		@Override
		public String getName() {
			return "automatic run";
		}
	},
	DRY_RUN {
		@Override
    public ActionExecutor getExecutor(Context context) {
      return new DryRunActionExecutor(context);
		}

		@Override
		public String getName() {
			return "dry run";
		}
	},
	VALIDATION {
		@Override
    public ValidationActionExecutor getExecutor(Context context) {
      return new ValidationActionExecutor(context);
		}

		@Override
		public String getName() {
			return "validation";
		}
	};

	public static Mode fromString(String modeName, Mode defaultMode) {
		return (StringUtils.isEmpty(modeName)) ? defaultMode : Mode.valueOf(modeName.toUpperCase());
	}

  public ActionExecutor getExecutor(Context context) {
		throw new IllegalArgumentException("Cannot create action executor in mode: " + name());
	}

	public boolean isRun() {
		return false;
	}

	abstract public String getName();
}