package com.lld.im.service.user.model.resp;

import com.lld.im.common.model.UserSession;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: teo
 *  
 */
@Data
public class UserOnlineStatusResp {

    private List<UserSession> session;

    private String customText;

    private Integer customStatus;

}
