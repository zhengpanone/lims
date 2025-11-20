package com.zp.lims.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * SysUserPost
 *
 * @author zhengpanone
 * @since 2022-12-01
 */
@Data
public class SysUserPost {


    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;
}
