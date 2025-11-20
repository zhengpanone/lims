package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.lims.common.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : zhengpanone
 * Date : 2025/6/20 14:03
 * Version : v1.0.0
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@TableName("refresh_token")
public class RefreshToken extends BaseEntity {

    private Long id;

    private String token;
    @TableField(value = "username")
    private String username;

    private LocalDateTime expiryDate;

    /**
     * 是否被撤销
     */
    private boolean isRevoked = false;
}
