package com.zp.lims.sys.controller.dto;

import lombok.Data;

/**
 * AdminDTO
 *
 * @author zhengpanone
 * @since 2022-11-27
 */
@Data
public class AdminDTO {
    Integer page;
    Integer limit;
    String name;
    String roles;
    String status;
}
