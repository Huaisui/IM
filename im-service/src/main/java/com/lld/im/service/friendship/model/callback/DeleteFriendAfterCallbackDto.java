package com.lld.im.service.friendship.model.callback;

import com.lld.im.service.friendship.model.req.FriendDto;
import lombok.Data;

/**
 * @description:
 * @author: teo
 *  
 */
@Data
public class DeleteFriendAfterCallbackDto {

    private String fromId;

    private String toId;
}
