package com.lld.im.codec.pack.user;

import com.lld.im.common.model.UserSession;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: teo
 *  
 */
@Data
public class UserCustomStatusChangeNotifyPack {

    private String customText;

    private Integer customStatus;

    private String userId;

}
