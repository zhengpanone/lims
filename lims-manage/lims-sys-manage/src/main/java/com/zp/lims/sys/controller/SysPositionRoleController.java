package com.zp.lims.sys.controller;

import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysPositionRole;
import com.zp.lims.sys.service.ISysPositionRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 职位角色管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "职位角色管理")
@RequestMapping("/positionRole")
@RequiredArgsConstructor
@Slf4j
public class SysPositionRoleController extends BaseController {
    
    private final ISysPositionRoleService sysPositionRoleService;

    @ApiOperation("根据职位ID查询角色")
    @GetMapping("/position/{positionId}")
    public R<List<SysPositionRole>> getByPositionId(@ApiParam("职位ID") @PathVariable Long positionId) {
        List<SysPositionRole> positionRoles = sysPositionRoleService.getByPositionId(positionId);
        return R.success(positionRoles);
    }

    @ApiOperation("根据角色ID查询职位")
    @GetMapping("/role/{roleId}")
    public R<List<SysPositionRole>> getByRoleId(@ApiParam("角色ID") @PathVariable Long roleId) {
        List<SysPositionRole> positionRoles = sysPositionRoleService.getByRoleId(roleId);
        return R.success(positionRoles);
    }

    @ApiOperation("为职位分配角色")
    @PostMapping("/assign/{positionId}")
    public R<Boolean> assignRolesToPosition(
            @ApiParam("职位ID") @PathVariable Long positionId,
            @ApiParam("角色ID列表") @RequestBody List<Long> roleIds) {
        Boolean result = sysPositionRoleService.assignRolesToPosition(positionId, roleIds);
        return R.success(result);
    }

    @ApiOperation("删除职位角色关联")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("关联ID") @PathVariable Long id) {
        boolean result = sysPositionRoleService.removeById(id);
        return R.success(result);
    }
} 