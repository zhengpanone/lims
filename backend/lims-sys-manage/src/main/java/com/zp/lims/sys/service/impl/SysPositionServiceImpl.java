package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysPosition;
import com.zp.lims.sys.mapper.SysPositionMapper;
import com.zp.lims.sys.service.ISysPositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * SysPositionServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 职位服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysPositionServiceImpl extends ServiceImpl<SysPositionMapper, SysPosition> implements ISysPositionService {

    @Override
    public SysPosition getByCode(String code) {
        LambdaQueryWrapper<SysPosition> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysPosition::getCode, code);
        return this.getOne(wrapper);
    }

    @Override
    public SysPosition getByName(String name) {
        LambdaQueryWrapper<SysPosition> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysPosition::getName, name);
        return this.getOne(wrapper);
    }
} 