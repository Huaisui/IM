package com.lld.im.service.group.model.resp;

import lombok.Data;

/**
 * @author: teo
 * @description:
 **/
@Data
public class GetRoleInGroupResp {

    private Long groupMemberId;

    private String memberId;

    private Integer role;

    private Long speakDate;

}