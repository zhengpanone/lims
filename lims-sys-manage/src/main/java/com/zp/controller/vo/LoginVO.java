package com.zp.controller.vo;

import lombok.Data;

import java.util.Date;

/**
 * UserVO
 *
 * @author zhengpanone
 * @since 2022-11-07
 */
@Data
public class LoginVO {
    private String  token;
    private Date expiresTime;
    private String  version;
    private UserInfo userInfo;

}
