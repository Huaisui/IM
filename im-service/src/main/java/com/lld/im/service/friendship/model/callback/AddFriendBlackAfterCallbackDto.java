package com.lld.im.service.friendship.model.callback;

import lombok.Data;

/**
 * @description:
 * @author: teo
 * @version: 1.0
 */
@Data
public class AddFriendBlackAfterCallbackDto {

    private String fromId;

    private String toId;
}
