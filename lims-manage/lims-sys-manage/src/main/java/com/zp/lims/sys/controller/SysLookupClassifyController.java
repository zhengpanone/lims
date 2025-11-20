package com.zp.lims.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysLookupClassifyEntity;
import com.zp.lims.sys.service.ISysLookupClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典分类控制器
 *
 * @author zhengp
 */
@RestController
@RequestMapping("/sys/lookup/classify")
public class SysLookupClassifyController extends BaseController {

    @Autowired
    private ISysLookupClassifyService sysLookupClassifyService;

    /**
     * 分页查询数据字典分类
     */
    @GetMapping("/list")
    public R list(SysLookupClassifyEntity classify) {
        Page<SysLookupClassifyEntity> page = new Page<>(1, 10);
        IPage<SysLookupClassifyEntity> list = sysLookupClassifyService.queryPage(page, classify);
        return R.success(list);
    }

    /**
     * 获取数据字典分类详细信息
     */
    @GetMapping("/{id}")
    public R getInfo(@PathVariable("id") Long id) {
        return R.success(sysLookupClassifyService.getById(id));
    }

    /**
     * 新增数据字典分类
     */
    @PostMapping
    public R add(@RequestBody SysLookupClassifyEntity classify) {
        return R.success(sysLookupClassifyService.saveClassify(classify));
    }

    /**
     * 修改数据字典分类
     */
    @PutMapping
    public R edit(@RequestBody SysLookupClassifyEntity classify) {
        return R.success(sysLookupClassifyService.updateClassify(classify));
    }

    /**
     * 删除数据字典分类
     */
    @DeleteMapping("/{id}")
    public R remove(@PathVariable Long id) {
        return R.success(sysLookupClassifyService.deleteClassify(id));
    }

    /**
     * 批量删除数据字典分类
     */
    @DeleteMapping("/batch/{ids}")
    public R batchRemove(@PathVariable Long[] ids) {
        return R.success(sysLookupClassifyService.deleteBatchClassify(ids));
    }
}
