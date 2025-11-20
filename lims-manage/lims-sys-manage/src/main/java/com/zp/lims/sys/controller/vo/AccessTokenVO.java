package com.zp.lims.sys.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : zhengpanone
 * Date : 2025/6/20 17:49
 * Version : v1.0.0
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenVO {
    private String accessToken;
    private String tokenType = "Bearer";
    private long expiresIn;
}
