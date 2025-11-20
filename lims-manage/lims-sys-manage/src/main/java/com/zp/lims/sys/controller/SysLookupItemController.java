package com.zp.lims.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysLookupItemEntity;
import com.zp.lims.sys.service.ISysLookupItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典项控制器
 *
 * @author zhengp
 */
@RestController
@RequestMapping("/sys/lookup/item")
public class SysLookupItemController extends BaseController {

    @Autowired
    private ISysLookupItemService sysLookupItemService;

    /**
     * 分页查询数据字典项
     */
    @GetMapping("/list")
    public R list(SysLookupItemEntity lookupItem) {
        Page<SysLookupItemEntity> page = new Page<>(1, 10);
        IPage<SysLookupItemEntity> list = sysLookupItemService.queryPage(page, lookupItem);
        return R.success(list);
    }

    /**
     * 获取数据字典项详细信息
     */
    @GetMapping("/{id}")
    public R getInfo(@PathVariable("id") Long id) {
        return R.success(sysLookupItemService.getById(id));
    }

    /**
     * 新增数据字典项
     */
    @PostMapping
    public R add(@RequestBody SysLookupItemEntity lookupItem) {
        return R.success(sysLookupItemService.saveLookupItem(lookupItem));
    }

    /**
     * 修改数据字典项
     */
    @PutMapping
    public R edit(@RequestBody SysLookupItemEntity lookupItem) {
        return R.success(sysLookupItemService.updateLookupItem(lookupItem));
    }

    /**
     * 删除数据字典项
     */
    @DeleteMapping("/{id}")
    public R remove(@PathVariable Long id) {
        return R.success(sysLookupItemService.deleteLookupItem(id));
    }

    /**
     * 批量删除数据字典项
     */
    @DeleteMapping("/batch/{ids}")
    public R batchRemove(@PathVariable Long[] ids) {
        return R.success(sysLookupItemService.deleteBatchLookupItem(ids));
    }
}
