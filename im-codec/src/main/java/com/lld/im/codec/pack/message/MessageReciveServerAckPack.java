package com.lld.im.codec.pack.message;

import lombok.Data;

/**
 * @description:
 * @author: teo
 *  
 */
@Data
public class MessageReciveServerAckPack {

    private Long messageKey;

    private String fromId;

    private String toId;

    private Long messageSequence;

    private Boolean serverSend;
}
