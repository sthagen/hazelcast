/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.sql.impl.exec.io;

/**
 * Core interface for inbound message processing.
 */
public interface InboundHandler {
    /**
     * Handle batch from the remote outbound handler.
     *
     * @param batch Data batch.
     * @param remainingMemory Amount of available memory as seen by the remote outbound handler.
     */
    void onBatch(InboundBatch batch, long remainingMemory);

    /**
     * A callback invoked the fragment that owns this handler is finished.
     */
    void onFragmentExecutionCompleted();
}
