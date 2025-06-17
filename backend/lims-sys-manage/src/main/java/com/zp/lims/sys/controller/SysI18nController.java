package com.zp.lims.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysI18nEntity;
import com.zp.lims.sys.service.ISysI18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 国际化信息控制器
 *
 * @author zhengp
 */
@RestController
@RequestMapping("/sys/i18n")
public class SysI18nController extends BaseController {

    @Autowired
    private ISysI18nService sysI18nService;

    /**
     * 分页查询国际化信息
     */
    @GetMapping("/list")
    public R<?> list(SysI18nEntity i18n) {
        Page<SysI18nEntity> page = new Page<>(1,10);
        IPage<SysI18nEntity> list = sysI18nService.queryPage(page, i18n);
        return R.success(list);
    }

    /**
     * 获取国际化信息详细信息
     */
    @GetMapping("/{id}")
    public R<?> getInfo(@PathVariable("id") Long id) {
        return R.success(sysI18nService.getById(id));
    }

    /**
     * 新增国际化信息
     */
    @PostMapping
    public R<?> add(@RequestBody SysI18nEntity i18n) {
        return R.success(sysI18nService.saveI18n(i18n));
    }

    /**
     * 修改国际化信息
     */
    @PutMapping
    public R<?> edit(@RequestBody SysI18nEntity i18n) {
        return R.success(sysI18nService.updateI18n(i18n));
    }

    /**
     * 删除国际化信息
     */
    @DeleteMapping("/{id}")
    public R<?> remove(@PathVariable Long id) {
        return R.success(sysI18nService.deleteI18n(id));
    }

    /**
     * 批量删除国际化信息
     */
    @DeleteMapping("/batch/{ids}")
    public R<?> batchRemove(@PathVariable Long[] ids) {
        return R.success(sysI18nService.deleteBatchI18n(ids));
    }
}
