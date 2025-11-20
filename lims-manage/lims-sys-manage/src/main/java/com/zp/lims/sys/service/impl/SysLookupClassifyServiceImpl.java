package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysLookupClassifyEntity;
import com.zp.lims.sys.mapper.SysLookupClassifyMapper;
import com.zp.lims.sys.service.ISysLookupClassifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 数据字典分类服务实现类
 *
 * @author zhengp
 */
@Service
public class SysLookupClassifyServiceImpl extends ServiceImpl<SysLookupClassifyMapper, SysLookupClassifyEntity> implements ISysLookupClassifyService {

    @Override
    public IPage<SysLookupClassifyEntity> queryPage(Page<SysLookupClassifyEntity> page, SysLookupClassifyEntity classify) {
        return this.baseMapper.queryPage(page, classify);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveClassify(SysLookupClassifyEntity classify) {
        return this.save(classify);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateClassify(SysLookupClassifyEntity classify) {
        return this.updateById(classify);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteClassify(Long id) {
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchClassify(Long[] ids) {
        return this.removeByIds(Arrays.asList(ids));
    }
}
