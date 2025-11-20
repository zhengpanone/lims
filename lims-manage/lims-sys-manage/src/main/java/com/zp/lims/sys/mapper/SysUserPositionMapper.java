package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysUserPosition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysUserPosition
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户职位表数据访问层
 */
public interface SysUserPositionMapper extends BaseMapper<SysUserPosition> {
    /**
     * 根据用户ID查询职位列表
     * @param userId
     * @return
     */
    List<SysUserPosition> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 根据职位ID查询用户列表
     * @param positionId
     * @return
     */
    List<SysUserPosition> selectByPositionId(@Param("positionId") Long positionId);
} 