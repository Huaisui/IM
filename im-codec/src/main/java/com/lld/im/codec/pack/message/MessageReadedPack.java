package com.lld.im.codec.pack.message;

import lombok.Data;

/**
 * @description:
 * @author: teo
 *
 */
@Data
public class MessageReadedPack {

    private long messageSequence;

    private String fromId;

    private String groupId;

    private String toId;

    private Integer conversationType;
}
