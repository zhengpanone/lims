package com.zp.sys.controller.dto;

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
public class UserDTO{
    @ApiModelProperty("用户名称")
    private String userName;
    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID集合")
    private List<Long> role;
    /**
     * 部门ID
     */
    @NotEmpty(message = "部门不能为空")
    @ApiModelProperty("部门ID")
    private Long deptId;

    /**
     * 岗位ID
     */
    @ApiModelProperty("岗位ID集合")
    private List<Long> post;

    /**
     * 新密码
     */
    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty("新密码")
    private String newPassword;

    /**
     * 验证码
     */
    private String code;
}
