package com.lld.im.common.model.message;

import lombok.Data;

/**
 * @description:
 * @author: teo
 * @version: 1.0
 */
@Data
public class DoStoreGroupMessageDto {

    private GroupChatMessageContent groupChatMessageContent;

    private ImMessageBody messageBody;

}
