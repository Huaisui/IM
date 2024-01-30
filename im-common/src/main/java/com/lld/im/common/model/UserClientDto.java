package com.lld.im.common.model;

import lombok.Data;

/**
 * @description:
 * @author: teo
 *  
 */
@Data
public class UserClientDto {

    private Integer appId;

    private Integer clientType;

    private String userId;

    private String imei;

}
