package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysI18nEntity;
import com.zp.lims.sys.mapper.SysI18nMapper;
import com.zp.lims.sys.service.ISysI18nService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 国际化信息服务实现类
 *
 * @author zhengp
 */
@Service
public class SysI18nServiceImpl extends ServiceImpl<SysI18nMapper, SysI18nEntity> implements ISysI18nService {

    @Override
    public IPage<SysI18nEntity> queryPage(Page<SysI18nEntity> page, SysI18nEntity i18n) {
        return this.baseMapper.queryPage(page, i18n);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveI18n(SysI18nEntity i18n) {
        return this.save(i18n);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateI18n(SysI18nEntity i18n) {
        return this.updateById(i18n);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteI18n(Long id) {
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchI18n(Long[] ids) {
        return this.removeByIds(Arrays.asList(ids));
    }
}
