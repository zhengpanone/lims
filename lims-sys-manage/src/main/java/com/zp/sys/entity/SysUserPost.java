package com.zp.sys.entity;

import lombok.Data;

/**
 * SysUserPost
 *
 * @author zhengpanone
 * @since 2022-12-01
 */
@Data
public class SysUserPost {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;
}
