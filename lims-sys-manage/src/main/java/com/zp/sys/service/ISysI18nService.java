package com.zp.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zp.sys.entity.SysI18nEntity;

public interface ISysI18nService {
    IPage<SysI18nEntity> queryI18nPage(IPage<SysI18nEntity> page, SysI18nEntity i18nEntity);
}
