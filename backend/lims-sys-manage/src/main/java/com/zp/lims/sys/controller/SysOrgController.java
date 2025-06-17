package com.zp.lims.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysOrg;
import com.zp.lims.sys.service.ISysOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "组织管理")
@RequestMapping("/org")
@RequiredArgsConstructor
@Slf4j
public class SysOrgController extends BaseController {
    
    private final ISysOrgService sysOrgService;

    @ApiOperation("分页查询组织")
    @GetMapping("/page")
    public R<Page<SysOrg>> page(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer current,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam("组织名称") @RequestParam(required = false) String name,
            @ApiParam("组织代码") @RequestParam(required = false) String orgCode) {
        
        Page<SysOrg> page = new Page<>(current, size);
        LambdaQueryWrapper<SysOrg> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, SysOrg::getName, name)
                .like(orgCode != null, SysOrg::getOrgCode, orgCode)
                .orderByAsc(SysOrg::getLevel)
                .orderByAsc(SysOrg::getId);
        
        Page<SysOrg> result = sysOrgService.page(page, wrapper);
        return R.success(result);
    }

    @ApiOperation("获取组织树")
    @GetMapping("/tree")
    public R<List<SysOrg>> getOrgTree() {
        List<SysOrg> orgTree = sysOrgService.getOrgTree();
        return R.success(orgTree);
    }

    @ApiOperation("根据ID查询组织")
    @GetMapping("/{id}")
    public R<SysOrg> getById(@ApiParam("组织ID") @PathVariable Long id) {
        SysOrg org = sysOrgService.getById(id);
        return R.success(org);
    }

    @ApiOperation("新增组织")
    @PostMapping
    public R<Boolean> save(@Validated @RequestBody SysOrg org) {
        boolean result = sysOrgService.save(org);
        return R.success(result);
    }

    @ApiOperation("更新组织")
    @PutMapping
    public R<Boolean> update(@Validated @RequestBody SysOrg org) {
        boolean result = sysOrgService.updateById(org);
        return R.success(result);
    }

    @ApiOperation("删除组织")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("组织ID") @PathVariable Long id) {
        boolean result = sysOrgService.removeById(id);
        return R.success(result);
    }

    @ApiOperation("根据父级ID查询子组织")
    @GetMapping("/children/{parentId}")
    public R<List<SysOrg>> getChildrenByParentId(@ApiParam("父级ID") @PathVariable Long parentId) {
        List<SysOrg> children = sysOrgService.getChildrenByParentId(parentId);
        return R.success(children);
    }
} 