package com.lld.im.service.user.model.resp;

import lombok.Data;

import java.util.List;

/**
 * @author: teo
 * @description:
 **/
@Data
public class ImportUserResp {

    private List<String> successId;

    private List<String> errorId;

}
