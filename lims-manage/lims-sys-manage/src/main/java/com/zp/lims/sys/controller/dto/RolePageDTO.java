package com.zp.lims.sys.controller.dto;

import com.zp.lims.common.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RoleDTO
 *
 * @author zhengpanone
 * @since 2022-11-30
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class RolePageDTO extends PageDTO {
    String name;
    StatusEnum status;

}
