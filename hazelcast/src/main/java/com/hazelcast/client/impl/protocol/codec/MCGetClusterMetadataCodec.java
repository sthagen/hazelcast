/*
 * Copyright (c) 2008-2021, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.client.impl.protocol.codec;

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.Generated;
import com.hazelcast.client.impl.protocol.codec.builtin.*;
import com.hazelcast.client.impl.protocol.codec.custom.*;

import javax.annotation.Nullable;

import static com.hazelcast.client.impl.protocol.ClientMessage.*;
import static com.hazelcast.client.impl.protocol.codec.builtin.FixedSizeTypesCodec.*;

/*
 * This file is auto-generated by the Hazelcast Client Protocol Code Generator.
 * To change this file, edit the templates or the protocol
 * definitions on the https://github.com/hazelcast/hazelcast-client-protocol
 * and regenerate it.
 */

/**
 * Gets the current metadata of a cluster.
 */
@Generated("4b3335c35463aad7016b8dae36cbda47")
public final class MCGetClusterMetadataCodec {
    //hex: 0x200E00
    public static final int REQUEST_MESSAGE_TYPE = 2100736;
    //hex: 0x200E01
    public static final int RESPONSE_MESSAGE_TYPE = 2100737;
    private static final int REQUEST_INITIAL_FRAME_SIZE = PARTITION_ID_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int RESPONSE_CURRENT_STATE_FIELD_OFFSET = RESPONSE_BACKUP_ACKS_FIELD_OFFSET + BYTE_SIZE_IN_BYTES;
    private static final int RESPONSE_CLUSTER_TIME_FIELD_OFFSET = RESPONSE_CURRENT_STATE_FIELD_OFFSET + BYTE_SIZE_IN_BYTES;
    private static final int RESPONSE_INITIAL_FRAME_SIZE = RESPONSE_CLUSTER_TIME_FIELD_OFFSET + LONG_SIZE_IN_BYTES;

    private MCGetClusterMetadataCodec() {
    }

    public static ClientMessage encodeRequest() {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        clientMessage.setRetryable(true);
        clientMessage.setOperationName("MC.GetClusterMetadata");
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[REQUEST_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, REQUEST_MESSAGE_TYPE);
        encodeInt(initialFrame.content, PARTITION_ID_FIELD_OFFSET, -1);
        clientMessage.add(initialFrame);
        return clientMessage;
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class ResponseParameters {

        /**
         * Current state of the cluster:
         * 0 - ACTIVE
         * 1 - NO_MIGRATION
         * 2 - FROZEN
         * 3 - PASSIVE
         * 4 - IN_TRANSITION (not allowed)
         */
        public byte currentState;

        /**
         * Current version of the member.
         */
        public java.lang.String memberVersion;

        /**
         * Current Jet version of the member.
         */
        public @Nullable java.lang.String jetVersion;

        /**
         * Cluster-wide time in milliseconds.
         */
        public long clusterTime;
    }

    public static ClientMessage encodeResponse(byte currentState, java.lang.String memberVersion, @Nullable java.lang.String jetVersion, long clusterTime) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[RESPONSE_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, RESPONSE_MESSAGE_TYPE);
        encodeByte(initialFrame.content, RESPONSE_CURRENT_STATE_FIELD_OFFSET, currentState);
        encodeLong(initialFrame.content, RESPONSE_CLUSTER_TIME_FIELD_OFFSET, clusterTime);
        clientMessage.add(initialFrame);

        StringCodec.encode(clientMessage, memberVersion);
        CodecUtil.encodeNullable(clientMessage, jetVersion, StringCodec::encode);
        return clientMessage;
    }

    public static MCGetClusterMetadataCodec.ResponseParameters decodeResponse(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        ResponseParameters response = new ResponseParameters();
        ClientMessage.Frame initialFrame = iterator.next();
        response.currentState = decodeByte(initialFrame.content, RESPONSE_CURRENT_STATE_FIELD_OFFSET);
        response.clusterTime = decodeLong(initialFrame.content, RESPONSE_CLUSTER_TIME_FIELD_OFFSET);
        response.memberVersion = StringCodec.decode(iterator);
        response.jetVersion = CodecUtil.decodeNullable(iterator, StringCodec::decode);
        return response;
    }
}
