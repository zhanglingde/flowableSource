/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.flowable.common.engine.api.engine;

import org.flowable.common.engine.api.Engine;

/**
 * Interface describing a listener that gets notified when certain event occurs, related to the engine lifecycle it is attached to.
 *
 * @author Frederik Heremans
 * @author Joram Barrez
 */
public interface EngineLifecycleListener {

    /**
     * Called right after the engine has been built.
     */
    void onEngineBuilt(Engine engine);

    /**
     * Called right after the engine has been closed.
     */
    void onEngineClosed(Engine engine);

}
