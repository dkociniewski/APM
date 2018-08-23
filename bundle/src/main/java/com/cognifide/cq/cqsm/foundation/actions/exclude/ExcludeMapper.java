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
package com.cognifide.cq.cqsm.foundation.actions.exclude;

import com.cognifide.cq.cqsm.api.actions.Action;
import com.cognifide.cq.cqsm.api.actions.annotations.Mapper;
import com.cognifide.cq.cqsm.api.actions.annotations.Mapping;
import java.util.Collections;
import java.util.List;

@Mapper("exclude")
public final class ExcludeMapper {

  public static final String REFERENCE = "Remove specified users and groups from current group.";

  @Mapping(
      args = {"authorizableId"},
      reference = REFERENCE
  )
  public Action mapAction(final String id) {
    return mapAction(Collections.singletonList(id));
  }

  @Mapping(
      args = {"authorizableIds"},
      reference = REFERENCE
  )
  public Action mapAction(final List<String> ids) {
    return new Exclude(ids);
  }

}