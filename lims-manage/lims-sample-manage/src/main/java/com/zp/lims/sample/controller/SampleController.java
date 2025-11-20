package com.zp.lims.sample.controller;

import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.sample.entity.SampleEntity;
import com.zp.lims.sample.service.ISampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 样品管理控制器
 */
@RestController
@RequestMapping("/sample")
public class SampleController extends BaseController {

    @Autowired
    private ISampleService sampleService;

    /**
     * 获取样品列表
     */
    @GetMapping("/list")
    public List<SampleEntity> list() {
        return sampleService.findSampleList();
    }

    /**
     * 获取样品详情
     */
    @GetMapping("/{id}")
    public SampleEntity getById(@PathVariable("id") Long id) {
        return sampleService.getSampleById(id);
    }

    /**
     * 新增样品
     */
    @PostMapping
    public boolean save(@RequestBody SampleEntity sample) {
        return sampleService.saveSample(sample);
    }

    /**
     * 更新样品
     */
    @PutMapping
    public boolean update(@RequestBody SampleEntity sample) {
        return sampleService.updateSample(sample);
    }

    /**
     * 删除样品
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return sampleService.deleteSample(id);
    }
}
