package com.zp.lims.sys.controller;

import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysUserPosition;
import com.zp.lims.sys.service.ISysUserPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户职位管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "用户职位管理")
@RequestMapping("/userPosition")
@RequiredArgsConstructor
@Slf4j
public class SysUserPositionController extends BaseController {
    
    private final ISysUserPositionService sysUserPositionService;

    @ApiOperation("根据用户ID查询职位")
    @GetMapping("/user/{userId}")
    public R<List<SysUserPosition>> getByUserId(@ApiParam("用户ID") @PathVariable Long userId) {
        List<SysUserPosition> userPositions = sysUserPositionService.getByUserId(userId);
        return R.success(userPositions);
    }

    @ApiOperation("根据职位ID查询用户")
    @GetMapping("/position/{positionId}")
    public R<List<SysUserPosition>> getByPositionId(@ApiParam("职位ID") @PathVariable Long positionId) {
        List<SysUserPosition> userPositions = sysUserPositionService.getByPositionId(positionId);
        return R.success(userPositions);
    }

    @ApiOperation("为用户分配职位")
    @PostMapping("/assign/{userId}")
    public R<Boolean> assignPositionsToUser(
            @ApiParam("用户ID") @PathVariable Long userId,
            @ApiParam("职位ID列表") @RequestBody List<Long> positionIds) {
        Boolean result = sysUserPositionService.assignPositionsToUser(userId, positionIds);
        return R.success(result);
    }

    @ApiOperation("删除用户职位关联")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("关联ID") @PathVariable Long id) {
        boolean result = sysUserPositionService.removeById(id);
        return R.success(result);
    }
} 