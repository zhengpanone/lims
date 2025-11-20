package com.zp.lims.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysPermission;
import com.zp.lims.sys.service.ISysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "权限管理")
@RequestMapping("/permission")
@RequiredArgsConstructor
@Slf4j
public class SysPermissionController extends BaseController {
    
    private final ISysPermissionService sysPermissionService;

    @ApiOperation("分页查询权限")
    @GetMapping("/page")
    public R<Page<SysPermission>> page(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer current,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam("权限名称") @RequestParam(required = false) String name,
            @ApiParam("权限代码") @RequestParam(required = false) String code,
            @ApiParam("权限类型") @RequestParam(required = false) String type) {
        
        Page<SysPermission> page = new Page<>(current, size);
        LambdaQueryWrapper<SysPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, SysPermission::getName, name)
                .like(code != null, SysPermission::getCode, code)
                .eq(type != null, SysPermission::getType, type)
                .orderByAsc(SysPermission::getSort)
                .orderByAsc(SysPermission::getId);
        
        Page<SysPermission> result = sysPermissionService.page(page, wrapper);
        return R.success(result);
    }

    @ApiOperation("获取权限树")
    @GetMapping("/tree")
    public R<List<SysPermission>> getPermissionTree() {
        List<SysPermission> permissionTree = sysPermissionService.getPermissionTree();
        return R.success(permissionTree);
    }

    @ApiOperation("根据角色ID查询权限")
    @GetMapping("/role/{roleId}")
    public R<List<SysPermission>> getByRoleId(@ApiParam("角色ID") @PathVariable Long roleId) {
        List<SysPermission> permissions = sysPermissionService.getByRoleId(roleId);
        return R.success(permissions);
    }

    @ApiOperation("根据用户ID查询权限")
    @GetMapping("/user/{userId}")
    public R<List<SysPermission>> getByUserId(@ApiParam("用户ID") @PathVariable Long userId) {
        List<SysPermission> permissions = sysPermissionService.getByUserId(userId);
        return R.success(permissions);
    }

    @ApiOperation("根据ID查询权限")
    @GetMapping("/{id}")
    public R<SysPermission> getById(@ApiParam("权限ID") @PathVariable Long id) {
        SysPermission permission = sysPermissionService.getById(id);
        return R.success(permission);
    }

    @ApiOperation("新增权限")
    @PostMapping
    public R<Boolean> save(@Validated @RequestBody SysPermission permission) {
        boolean result = sysPermissionService.save(permission);
        return R.success(result);
    }

    @ApiOperation("更新权限")
    @PutMapping
    public R<Boolean> update(@Validated @RequestBody SysPermission permission) {
        boolean result = sysPermissionService.updateById(permission);
        return R.success(result);
    }

    @ApiOperation("删除权限")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("权限ID") @PathVariable Long id) {
        boolean result = sysPermissionService.removeById(id);
        return R.success(result);
    }
} 