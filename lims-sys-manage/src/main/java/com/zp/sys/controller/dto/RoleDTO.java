package com.zp.sys.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RoleDTO
 *
 * @author zhengpanone
 * @since 2022-11-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDTO extends CommonDTO {
    String name;
}
