package com.lld.im.common.model.message;

import lombok.Data;

/**
 * @description:
 * @author: teo
 *  
 */
@Data
public class DoStoreGroupMessageDto {

    private GroupChatMessageContent groupChatMessageContent;

    private ImMessageBody messageBody;

}
