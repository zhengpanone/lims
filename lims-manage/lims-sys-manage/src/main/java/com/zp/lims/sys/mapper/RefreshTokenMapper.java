package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.RefreshToken;
import com.zp.lims.sys.entity.SysUserOrg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * RefreshTokenMapper
 * @author zhengpanone
 * @description: 刷新令牌Mapper接口
 * @since 2022-11-28
 */
public interface RefreshTokenMapper extends BaseMapper<RefreshToken> {

    /**
     * 根据token查询刷新令牌
     */
    @Select("SELECT * FROM refresh_token WHERE token = #{token} AND deleted = 0")
    RefreshToken findByToken(@Param("token") String token);

    /**
     * 根据用户名查询刷新令牌
     */
    @Select("SELECT * FROM refresh_token WHERE username = #{username} AND deleted = 0 ORDER BY create_time DESC LIMIT 1")
    RefreshToken findByUsername(@Param("username") String username);

    /**
     * 根据用户名撤销所有刷新令牌
     */
    @Update("UPDATE refresh_token SET is_revoked = 1, update_time = NOW() WHERE username = #{username} AND deleted = 0")
    int revokeAllByUsername(@Param("username") String username);

    /**
     * 撤销指定token
     */
    @Update("UPDATE refresh_token SET is_revoked = 1, update_time = NOW() WHERE token = #{token} AND deleted = 0")
    int revokeByToken(@Param("token") String token);

    /**
     * 删除过期的刷新令牌
     */
    @Delete("DELETE FROM refresh_token WHERE expiry_date < NOW() OR deleted = 1")
    int deleteExpiredTokens();
} 