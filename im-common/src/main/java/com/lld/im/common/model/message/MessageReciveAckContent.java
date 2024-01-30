package com.lld.im.common.model.message;

import com.lld.im.common.model.ClientInfo;
import lombok.Data;
import sun.dc.pr.PRError;

/**
 * @description:
 * @author: teo
 *  
 */
@Data
public class MessageReciveAckContent extends ClientInfo {

    private Long messageKey;

    private String fromId;

    private String toId;

    private Long messageSequence;


}
