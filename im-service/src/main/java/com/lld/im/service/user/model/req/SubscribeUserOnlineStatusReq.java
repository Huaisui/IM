package com.lld.im.service.user.model.req;

import com.lld.im.common.model.RequestBase;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: teo
 *  
 */
@Data
public class SubscribeUserOnlineStatusReq extends RequestBase {

    private List<String> subUserId;

    private Long subTime;


}
