package com.lld.im.common.model.message;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: teo
 *  
 */
@Data
public class GroupChatMessageContent extends MessageContent {

    private String groupId;

    private List<String> memberId;

}
