package com.lld.im.service.group.model.req;

import com.lld.im.common.model.RequestBase;
import lombok.Data;

/**
 * @author: teo
 * @description:
 **/
@Data
public class GetGroupReq extends RequestBase {

    private String groupId;

}
