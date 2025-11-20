package com.zp.lims.sys.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * UserDTO
 *
 * @author zhengpanone
 * @since 2022-12-01
 */
@Data
@ApiModel("新增修改用户校验类")
public class UserUpdateDTO {

    private String id;
    /**
     * 状态
     */
    private Integer status;
}
