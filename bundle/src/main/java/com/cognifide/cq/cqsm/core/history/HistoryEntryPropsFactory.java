/*-
 * ========================LICENSE_START=================================
 * AEM Permission Management
 * %%
 * Copyright (C) 2018 Cognifide Limited
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
package com.cognifide.cq.cqsm.core.history;

import com.cognifide.cq.cqsm.api.scripts.Script;
import com.cognifide.cq.cqsm.core.scripts.ScriptImpl;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.factory.MissingElementsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HistoryEntryPropsFactory {

	private static final Logger LOG = LoggerFactory.getLogger(HistoryEntryPropsFactory.class);

	private Resource resource;

	private Script script;

	public HistoryEntryPropsFactory(Resource resource) {
		this.resource = resource;
		final Resource scriptResource = this.resource.getChild(HistoryHelper.SCRIPT_HISTORY_FILE_NAME);
		try {
			this.script = scriptResource.adaptTo(ScriptImpl.class);
		} catch (MissingElementsException exception) {
			LOG.error("HISTORY_PROP_FACT_NO_SCRIPT",
				String.format("Can't find script for resource: %s", resource.getPath()));
			throw exception;
		}
	}

	public Timestamp getLastModificationTimestamp() {
		return new Timestamp(script.getLastModified().getTime());
	}

	public Timestamp getLastDryRunTimestamp() {
		final Optional<Date> lastDryExecutionDate = Optional.ofNullable(script.getDryRunLast());

		final Timestamp lastDryRunTimestamp = lastDryExecutionDate.map(date -> new Timestamp(date.getTime())).orElse(null);
		return lastDryRunTimestamp;
	}

	public Boolean isLastDryRunSuccessful() {
		return script.isDryRunSuccessful();
	}

	public String getFilePath() {
		return String.format("%s/%s", resource.getPath(), HistoryHelper.SCRIPT_HISTORY_FILE_NAME);
	}
}