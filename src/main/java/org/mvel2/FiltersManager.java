/*
 * Copyright 2017 JBoss by Red Hat.
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
package org.mvel2;

import org.openjdk.nashorn.api.scripting.ClassFilter;

/**
 * This interface works on providing MVEL with the ability of creating
 * filter for classes which are going to be invoked from the HTML and execute
 * during the process of rendering MVEL template.
 *
 * All what you need to start using this interface and filtering MVEL is to
 * add a property to ParserContext once before evaluating any template or
 * expression. So MVEL is going to filter the ability of reaching any of
 * the classes depends on what are you going to implement at
 * (class FiltersManagerClass implements
 * FiltersManager) in your project.
 *
 * NOTE: anytime exposeToScripts() returns false, that means velocity is going
 * to reject the access to this class through HTML developers.
 *
 *
 * e.g.: ParserContext ctx = new ParserContext();
 *       ctx.addProperty("mvel.filtersManager", "com.mvel.test.FiltersManager");
 *       MVEL.evalToString("....", ....);
 *
 * @author Mohammed F. Ouda
 */
public interface FiltersManager {

    void registerClassFilter(ClassFilter filter);

    void deregisterClassFilter(ClassFilter filter);

    boolean exposeToScripts(String classNameString);

    boolean exposeObjectToScripts(Object ob);
}
