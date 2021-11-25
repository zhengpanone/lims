package com.zp.service;

import com.zp.IdWorker;
import com.zp.dao.SysLogDao;
import com.zp.module.SysLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SysLogService
 *
 * @author zhengpanone
 * @since 2021-11-17
 */
@Service
public class SysLogService {
    @Resource
    SysLogDao sysLogDao;
    @Autowired
    private IdWorker idWorker;

    @Async
    public void save(SysLogEntity sysLogEntity) {
        String id = idWorker.nextId() + "";
        sysLogEntity.setId(id);
        sysLogDao.save(sysLogEntity);
    }
}
