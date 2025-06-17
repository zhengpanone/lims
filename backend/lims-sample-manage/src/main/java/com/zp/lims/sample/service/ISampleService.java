package com.zp.lims.sample.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sample.entity.SampleEntity;

import java.util.List;

/**
 * 样品服务接口
 */
public interface ISampleService extends IService<SampleEntity> {

    /**
     * 查询样品列表
     * @return 样品列表
     */
    List<SampleEntity> findSampleList();

    /**
     * 根据ID查询样品
     * @param sampleId 样品ID
     * @return 样品实体
     */
    SampleEntity getSampleById(Long sampleId);

    /**
     * 保存样品
     * @param sampleEntity 样品实体
     * @return 是否保存成功
     */
    boolean saveSample(SampleEntity sampleEntity);

    /**
     * 更新样品
     * @param sampleEntity 样品实体
     * @return 是否更新成功
     */
    boolean updateSample(SampleEntity sampleEntity);

    /**
     * 删除样品
     * @param sampleId 样品ID
     * @return 是否删除成功
     */
    boolean deleteSample(Long sampleId);
}
