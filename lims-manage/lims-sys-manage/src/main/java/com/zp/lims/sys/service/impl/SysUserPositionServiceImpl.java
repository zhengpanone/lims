package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysUserPosition;
import com.zp.lims.sys.mapper.SysUserPositionMapper;
import com.zp.lims.sys.service.ISysUserPositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysUserPositionServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户职位服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserPositionServiceImpl extends ServiceImpl<SysUserPositionMapper, SysUserPosition> implements ISysUserPositionService {

    @Override
    public List<SysUserPosition> getByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<SysUserPosition> getByPositionId(Long positionId) {
        return baseMapper.selectByPositionId(positionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignPositionsToUser(Long userId, List<Long> positionIds) {
        // 先删除用户原有职位
        LambdaQueryWrapper<SysUserPosition> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserPosition::getUserId, userId);
        this.remove(wrapper);
        
        // 分配新职位
        if (positionIds != null && !positionIds.isEmpty()) {
            for (Long positionId : positionIds) {
                SysUserPosition userPosition = new SysUserPosition();
                userPosition.setUserId(userId);
                userPosition.setPositionId(positionId);
                this.save(userPosition);
            }
        }
        return true;
    }
} 