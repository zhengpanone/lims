package com.zp.lims.sys.controller.vo;

import com.zp.lims.common.enums.StatusEnum;
import lombok.Data;

/**
 * AdminVO
 *
 * @author zhengpanone
 * @since 2022-11-27
 */
@Data
public class RoleVO {
    String id;
    String roleName;
    StatusEnum status;
}
