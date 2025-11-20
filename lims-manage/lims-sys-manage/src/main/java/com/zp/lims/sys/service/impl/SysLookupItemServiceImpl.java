package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysLookupItemEntity;
import com.zp.lims.sys.mapper.SysLookupItemMapper;
import com.zp.lims.sys.service.ISysLookupItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 数据字典项服务实现类
 *
 * @author zhengp
 */
@Service
public class SysLookupItemServiceImpl extends ServiceImpl<SysLookupItemMapper, SysLookupItemEntity> implements ISysLookupItemService {

    @Override
    public IPage<SysLookupItemEntity> queryPage(Page<SysLookupItemEntity> page, SysLookupItemEntity lookupItem) {
        return this.baseMapper.queryPage(page, lookupItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveLookupItem(SysLookupItemEntity lookupItem) {
        return this.save(lookupItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateLookupItem(SysLookupItemEntity lookupItem) {
        return this.updateById(lookupItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteLookupItem(Long id) {
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchLookupItem(Long[] ids) {
        return this.removeByIds(Arrays.asList(ids));
    }
}
