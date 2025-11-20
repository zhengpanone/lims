package com.zp.lims.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysUserGroup;
import com.zp.lims.sys.service.ISysUserGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户组管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "用户组管理")
@RequestMapping("/userGroup")
@RequiredArgsConstructor
@Slf4j
public class SysUserGroupController extends BaseController {
    
    private final ISysUserGroupService sysUserGroupService;

    @ApiOperation("分页查询用户组")
    @GetMapping("/page")
    public R<Page<SysUserGroup>> page(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer current,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam("用户组名称") @RequestParam(required = false) String name) {
        
        Page<SysUserGroup> page = new Page<>(current, size);
        LambdaQueryWrapper<SysUserGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, SysUserGroup::getName, name)
                .orderByAsc(SysUserGroup::getId);
        
        Page<SysUserGroup> result = sysUserGroupService.page(page, wrapper);
        return R.success(result);
    }

    @ApiOperation("获取所有用户组")
    @GetMapping("/list")
    public R<List<SysUserGroup>> list() {
        List<SysUserGroup> list = sysUserGroupService.list();
        return R.success(list);
    }

    @ApiOperation("根据ID查询用户组")
    @GetMapping("/{id}")
    public R<SysUserGroup> getById(@ApiParam("用户组ID") @PathVariable Long id) {
        SysUserGroup userGroup = sysUserGroupService.getById(id);
        return R.success(userGroup);
    }

    @ApiOperation("新增用户组")
    @PostMapping
    public R<Boolean> save(@Validated @RequestBody SysUserGroup userGroup) {
        boolean result = sysUserGroupService.save(userGroup);
        return R.success(result);
    }

    @ApiOperation("更新用户组")
    @PutMapping
    public R<Boolean> update(@Validated @RequestBody SysUserGroup userGroup) {
        boolean result = sysUserGroupService.updateById(userGroup);
        return R.success(result);
    }

    @ApiOperation("删除用户组")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("用户组ID") @PathVariable Long id) {
        boolean result = sysUserGroupService.removeById(id);
        return R.success(result);
    }
} 