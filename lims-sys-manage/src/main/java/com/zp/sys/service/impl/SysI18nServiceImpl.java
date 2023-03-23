package com.zp.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.sys.entity.SysI18nEntity;
import com.zp.sys.mapper.SysI18nMapper;
import com.zp.sys.service.ISysI18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysI18nServiceImpl extends ServiceImpl<SysI18nMapper, SysI18nEntity> implements ISysI18nService {
    @Autowired
    private SysI18nMapper sysI18nMapper;

    public IPage<SysI18nEntity> queryI18nPage(IPage<SysI18nEntity> page,SysI18nEntity i18nEntity){
        return sysI18nMapper.queryI18nPage(page,i18nEntity);
    }
}
