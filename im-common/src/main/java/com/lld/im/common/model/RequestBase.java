package com.lld.im.common.model;

import lombok.Data;

/**
 * @description:
 * @author: teo
 *  
 */
@Data
public class RequestBase {
    private Integer appId;

    private String operater;

    private Integer clientType;

    private String imei;
}
