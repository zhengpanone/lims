package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysOrg;
import com.zp.lims.sys.mapper.SysOrgMapper;
import com.zp.lims.sys.service.ISysOrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysOrgServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 组织服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements ISysOrgService {

    @Override
    public SysOrg getByOrgCode(String orgCode) {
        LambdaQueryWrapper<SysOrg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysOrg::getOrgCode, orgCode);
        return this.getOne(wrapper);
    }

    @Override
    public List<SysOrg> getOrgTree() {
        // 获取所有组织
        List<SysOrg> allOrgs = this.list();
        // TODO: 构建树形结构
        return allOrgs;
    }

    @Override
    public List<SysOrg> getChildrenByParentId(Long parentId) {
        LambdaQueryWrapper<SysOrg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysOrg::getParentId, parentId);
        return this.list(wrapper);
    }
} 