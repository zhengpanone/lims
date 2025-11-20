package com.zp.lims.sys.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * 登录响应VO
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@Accessors(chain = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "登录响应信息")
public class LoginResponseVO {

    @ApiModelProperty(value = "访问令牌")
    private String accessToken;

    @ApiModelProperty(value = "刷新令牌")
    private String refreshToken;

    @ApiModelProperty(value = "令牌类型")
    @Builder.Default
    private String tokenType = "Bearer";

    @ApiModelProperty(value = "过期时间(秒)")
    private Long expiresIn;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户头像")
    private String avatar;
} 