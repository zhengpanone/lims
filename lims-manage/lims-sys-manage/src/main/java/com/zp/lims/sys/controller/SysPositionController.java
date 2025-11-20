package com.zp.lims.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysPosition;
import com.zp.lims.sys.service.ISysPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 职位管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "职位管理")
@RequestMapping("/position")
@RequiredArgsConstructor
@Slf4j
public class SysPositionController extends BaseController {
    
    private final ISysPositionService sysPositionService;

    @ApiOperation("分页查询职位")
    @GetMapping("/page")
    public R<Page<SysPosition>> page(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer current,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam("职位名称") @RequestParam(required = false) String name,
            @ApiParam("职位代码") @RequestParam(required = false) String code) {
        
        Page<SysPosition> page = new Page<>(current, size);
        LambdaQueryWrapper<SysPosition> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, SysPosition::getName, name)
                .like(code != null, SysPosition::getCode, code)
                .orderByAsc(SysPosition::getId);
        
        Page<SysPosition> result = sysPositionService.page(page, wrapper);
        return R.success(result);
    }

    @ApiOperation("获取所有职位")
    @GetMapping("/list")
    public R<List<SysPosition>> list() {
        List<SysPosition> list = sysPositionService.list();
        return R.success(list);
    }

    @ApiOperation("根据ID查询职位")
    @GetMapping("/{id}")
    public R<SysPosition> getById(@ApiParam("职位ID") @PathVariable Long id) {
        SysPosition position = sysPositionService.getById(id);
        return R.success(position);
    }

    @ApiOperation("新增职位")
    @PostMapping
    public R<Boolean> save(@Validated @RequestBody SysPosition position) {
        boolean result = sysPositionService.save(position);
        return R.success(result);
    }

    @ApiOperation("更新职位")
    @PutMapping
    public R<Boolean> update(@Validated @RequestBody SysPosition position) {
        boolean result = sysPositionService.updateById(position);
        return R.success(result);
    }

    @ApiOperation("删除职位")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("职位ID") @PathVariable Long id) {
        boolean result = sysPositionService.removeById(id);
        return R.success(result);
    }
} 