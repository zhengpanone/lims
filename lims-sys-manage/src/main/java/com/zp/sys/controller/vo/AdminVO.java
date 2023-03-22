package com.zp.sys.controller.vo;

import lombok.Data;

/**
 * AdminVO
 *
 * @author zhengpanone
 * @since 2022-11-27
 */
@Data
public class AdminVO {
    String id;
    String account;
    String realName;
    Long loginCount;
    Integer status;
    String[] roles;
}
