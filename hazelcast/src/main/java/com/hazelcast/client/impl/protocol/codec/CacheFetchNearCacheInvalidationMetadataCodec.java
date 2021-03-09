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
 * Fetches invalidation metadata from partitions of map.
 */
@Generated("22325f4e98680c351bb985286d787c25")
public final class CacheFetchNearCacheInvalidationMetadataCodec {
    //hex: 0x131E00
    public static final int REQUEST_MESSAGE_TYPE = 1252864;
    //hex: 0x131E01
    public static final int RESPONSE_MESSAGE_TYPE = 1252865;
    private static final int REQUEST_UUID_FIELD_OFFSET = PARTITION_ID_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int REQUEST_INITIAL_FRAME_SIZE = REQUEST_UUID_FIELD_OFFSET + UUID_SIZE_IN_BYTES;
    private static final int RESPONSE_INITIAL_FRAME_SIZE = RESPONSE_BACKUP_ACKS_FIELD_OFFSET + BYTE_SIZE_IN_BYTES;

    private CacheFetchNearCacheInvalidationMetadataCodec() {
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class RequestParameters {

        /**
         * names of the caches
         */
        public java.util.List<java.lang.String> names;

        /**
         * Address of the member.
         */
        public java.util.UUID uuid;
    }

    public static ClientMessage encodeRequest(java.util.Collection<java.lang.String> names, java.util.UUID uuid) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        clientMessage.setRetryable(false);
        clientMessage.setOperationName("Cache.FetchNearCacheInvalidationMetadata");
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[REQUEST_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, REQUEST_MESSAGE_TYPE);
        encodeInt(initialFrame.content, PARTITION_ID_FIELD_OFFSET, -1);
        encodeUUID(initialFrame.content, REQUEST_UUID_FIELD_OFFSET, uuid);
        clientMessage.add(initialFrame);
        ListMultiFrameCodec.encode(clientMessage, names, StringCodec::encode);
        return clientMessage;
    }

    public static CacheFetchNearCacheInvalidationMetadataCodec.RequestParameters decodeRequest(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        RequestParameters request = new RequestParameters();
        ClientMessage.Frame initialFrame = iterator.next();
        request.uuid = decodeUUID(initialFrame.content, REQUEST_UUID_FIELD_OFFSET);
        request.names = ListMultiFrameCodec.decode(iterator, StringCodec::decode);
        return request;
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class ResponseParameters {

        /**
         * Map of partition ids and sequence number of invalidations mapped by the cache name.
         */
        public java.util.List<java.util.Map.Entry<java.lang.String, java.util.List<java.util.Map.Entry<java.lang.Integer, java.lang.Long>>>> namePartitionSequenceList;

        /**
         * Map of member UUIDs mapped by the partition ids of invalidations.
         */
        public java.util.List<java.util.Map.Entry<java.lang.Integer, java.util.UUID>> partitionUuidList;
    }
    public static ClientMessage encodeResponse(java.util.Collection<java.util.Map.Entry<java.lang.String, java.util.List<java.util.Map.Entry<java.lang.Integer, java.lang.Long>>>> namePartitionSequenceList, java.util.Collection<java.util.Map.Entry<java.lang.Integer, java.util.UUID>> partitionUuidList) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[RESPONSE_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, RESPONSE_MESSAGE_TYPE);
        clientMessage.add(initialFrame);

        EntryListCodec.encode(clientMessage, namePartitionSequenceList, StringCodec::encode, EntryListIntegerLongCodec::encode);
        EntryListIntegerUUIDCodec.encode(clientMessage, partitionUuidList);
        return clientMessage;
    }

    public static CacheFetchNearCacheInvalidationMetadataCodec.ResponseParameters decodeResponse(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        ResponseParameters response = new ResponseParameters();
        //empty initial frame
        iterator.next();
        response.namePartitionSequenceList = EntryListCodec.decode(iterator, StringCodec::decode, EntryListIntegerLongCodec::decode);
        response.partitionUuidList = EntryListIntegerUUIDCodec.decode(iterator);
        return response;
    }

}
