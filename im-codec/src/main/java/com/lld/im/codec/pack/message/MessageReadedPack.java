package com.lld.im.codec.pack.message;

import lombok.Data;

/**
 * @description:
 * @author: teo
 * @version: 1.0
 */
@Data
public class MessageReadedPack {

    private long messageSequence;

    private String fromId;

    private String groupId;

    private String toId;

    private Integer conversationType;
}
