package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysPosition;

/**
 * ISysPositionService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 职位服务接口
 */
public interface ISysPositionService extends IService<SysPosition> {
    /**
     * 根据职位代码查询职位
     * @param code
     * @return
     */
    SysPosition getByCode(String code);
    
    /**
     * 根据职位名称查询职位
     * @param name
     * @return
     */
    SysPosition getByName(String name);
} 