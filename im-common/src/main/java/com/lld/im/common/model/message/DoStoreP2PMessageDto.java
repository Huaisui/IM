package com.lld.im.common.model.message;

import lombok.Data;

/**
 * @description:
 * @author: teo
 * @version: 1.0
 */
@Data
public class DoStoreP2PMessageDto {

    private MessageContent messageContent;

    private ImMessageBody messageBody;

}
