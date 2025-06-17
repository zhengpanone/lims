package com.zp.lims.sys.controller;

import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysRoleMutex;
import com.zp.lims.sys.service.ISysRoleMutexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色互斥管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "角色互斥管理")
@RequestMapping("/roleMutex")
@RequiredArgsConstructor
@Slf4j
public class SysRoleMutexController extends BaseController {
    
    private final ISysRoleMutexService sysRoleMutexService;

    @ApiOperation("根据角色ID查询互斥角色")
    @GetMapping("/role/{roleId}")
    public R<List<SysRoleMutex>> getByRoleId(@ApiParam("角色ID") @PathVariable Long roleId) {
        List<SysRoleMutex> roleMutexes = sysRoleMutexService.getByRoleId(roleId);
        return R.success(roleMutexes);
    }

    @ApiOperation("检查两个角色是否互斥")
    @GetMapping("/check")
    public R<Boolean> isMutex(
            @ApiParam("角色ID1") @RequestParam Long roleId1,
            @ApiParam("角色ID2") @RequestParam Long roleId2) {
        Boolean isMutex = sysRoleMutexService.isMutex(roleId1, roleId2);
        return R.success(isMutex);
    }

    @ApiOperation("新增角色互斥关系")
    @PostMapping
    public R<Boolean> save(@Validated @RequestBody SysRoleMutex roleMutex) {
        boolean result = sysRoleMutexService.save(roleMutex);
        return R.success(result);
    }

    @ApiOperation("删除角色互斥关系")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("互斥关系ID") @PathVariable Long id) {
        boolean result = sysRoleMutexService.removeById(id);
        return R.success(result);
    }
} 