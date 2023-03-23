package com.zp.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.sys.entity.SysLookupClassifyEntity;

public interface ISysLookupClassifyService extends IService<SysLookupClassifyEntity> {
    Boolean addLookupClassify(SysLookupClassifyEntity classify);
}
