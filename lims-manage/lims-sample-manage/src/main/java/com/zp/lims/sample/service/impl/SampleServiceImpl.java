package com.zp.lims.sample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sample.entity.SampleEntity;
import com.zp.lims.sample.mapper.SampleMapper;
import com.zp.lims.sample.service.ISampleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 样品服务实现类
 */
@Service
public class SampleServiceImpl extends ServiceImpl<SampleMapper, SampleEntity> implements ISampleService {

    @Override
    public List<SampleEntity> findSampleList() {
        return baseMapper.fineSampleList();
    }

    @Override
    public SampleEntity getSampleById(Long sampleId) {
        return this.getById(sampleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSample(SampleEntity sampleEntity) {
        return this.save(sampleEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSample(SampleEntity sampleEntity) {
        return this.updateById(sampleEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteSample(Long sampleId) {
        return this.removeById(sampleId);
    }
}
