package com.lld.im.service.friendship.service;

import com.lld.im.common.ResponseVO;
import com.lld.im.service.friendship.model.req.AddFriendShipGroupMemberReq;
import com.lld.im.service.friendship.model.req.DeleteFriendShipGroupMemberReq;

/**
 * @author: teo
 * @description:
 **/
public interface ImFriendShipGroupMemberService {

    public ResponseVO addGroupMember(AddFriendShipGroupMemberReq req);

    public ResponseVO delGroupMember(DeleteFriendShipGroupMemberReq req);

    public int doAddGroupMember(Long groupId, String toId);

    public int clearGroupMember(Long groupId);
}
