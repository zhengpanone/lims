package com.zp.sys.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotNull;

/**
 * LoginForm
 *
 * @author zhengpanone
 * @since 2022-11-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @NotNull(message = "用户名不能为空")
    private String account;


    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 32, message = "密码长度在6-32之间")
    private String pwd;

    @NotNull(message = "验证码不能为空")
    @Length(min = 4,max = 4, message = "密码长度在6-32之间")
    private String imgCode;

}
