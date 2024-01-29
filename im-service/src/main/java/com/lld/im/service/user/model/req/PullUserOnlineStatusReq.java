package com.lld.im.service.user.model.req;

import com.lld.im.common.model.RequestBase;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: teo
 * @version: 1.0
 */
@Data
public class PullUserOnlineStatusReq extends RequestBase {

    private List<String> userList;

}