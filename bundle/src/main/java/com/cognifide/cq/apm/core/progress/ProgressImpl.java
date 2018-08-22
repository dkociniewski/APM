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
package com.cognifide.cq.apm.core.progress;

import com.cognifide.cq.apm.api.actions.ActionDescriptor;
import com.cognifide.cq.apm.api.actions.ActionResult;
import com.cognifide.cq.apm.api.logger.Message;
import com.cognifide.cq.apm.api.logger.Progress;
import com.cognifide.cq.apm.api.logger.ProgressEntry;
import com.cognifide.cq.apm.api.logger.Status;
import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;

public class ProgressImpl implements Progress {

	private final List<ProgressEntry> entries;

	private final String executor;


	public ProgressImpl(String executor) {
		this(executor, new LinkedList<ProgressEntry>());
	}

	public ProgressImpl(String executor, List<ProgressEntry> entries) {
		this.executor = executor;
		this.entries = entries;
	}

	@Override
	public List<ProgressEntry> getEntries() {
		return Lists.newLinkedList(entries);
	}

	@Override
	public void addEntry(ActionDescriptor descriptor, ActionResult result) {
		this.entries.add(new ProgressEntry(descriptor, result));
	}

	@Override
	public void addEntry(Message message, Status status) {
		this.entries.add(new ProgressEntry(message, status));
	}

	@Override
	public boolean isSuccess() {
		return ProgressHelper.calculateSuccess(entries);
	}

	@Override
	public ProgressEntry getLastError() {
		for (int i = entries.size() - 1; i >= 0; i--) {
			final ProgressEntry entry = entries.get(i);

			if (entry.getStatus().equals(Status.ERROR)) {
				return entry;
			}
		}

		return null;
	}

	@Override
	public String getExecutor() {
		return executor;
	}
}