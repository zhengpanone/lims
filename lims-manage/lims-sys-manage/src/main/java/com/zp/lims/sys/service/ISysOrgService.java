package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysOrg;

import java.util.List;

/**
 * ISysOrgService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 组织服务接口
 */
public interface ISysOrgService extends IService<SysOrg> {
    /**
     * 根据组织代码查询组织
     * @param orgCode
     * @return
     */
    SysOrg getByOrgCode(String orgCode);
    
    /**
     * 获取组织树结构
     * @return
     */
    List<SysOrg> getOrgTree();
    
    /**
     * 根据父级ID查询子组织
     * @param parentId
     * @return
     */
    List<SysOrg> getChildrenByParentId(Long parentId);
} 