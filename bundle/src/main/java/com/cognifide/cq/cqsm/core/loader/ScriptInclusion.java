/*
 * ========================LICENSE_START=================================
 * AEM Permission Management
 * %%
 * Copyright (C) 2013 Cognifide Limited
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

package com.cognifide.cq.cqsm.core.loader;

import com.cognifide.apm.antlr.ApmLangParser.ScriptInclusionContext;
import com.cognifide.cq.cqsm.core.antlr.StringLiteral;

public final class ScriptInclusion {

  private final String path;
  private final ScriptInclusionContext inclusion;

  public ScriptInclusion(String path, ScriptInclusionContext inclusion) {
    this.path = path;
    this.inclusion = inclusion;
  }

  public static ScriptInclusion of(ScriptInclusionContext inclusion) {
    String path = StringLiteral.getValue(inclusion.path().STRING_LITERAL());
    return new ScriptInclusion(path, inclusion);
  }

  public String getPath() {
    return path;
  }

  public ScriptInclusionContext getInclusion() {
    return inclusion;
  }
}
