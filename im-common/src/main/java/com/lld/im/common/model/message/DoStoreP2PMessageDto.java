package com.lld.im.common.model.message;

import lombok.Data;

/**
 * @description:
 * @author: teo
 *
 */
@Data
public class DoStoreP2PMessageDto {

    private MessageContent messageContent;

    private ImMessageBody messageBody;

}
