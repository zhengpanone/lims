package com.zp.lims.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.PageResult;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.controller.dto.UserDTO;
import com.zp.lims.sys.entity.SysUser;
import com.zp.lims.sys.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "用户管理")
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class SysUserController extends BaseController {

    private final ISysUserService sysUserService;

    @ApiOperation("分页查询用户")
    @GetMapping("/page")
    public R<PageResult<SysUser>> page(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer current,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam("用户名") @RequestParam(required = false) String userName,
            @ApiParam("邮箱") @RequestParam(required = false) String email) {

        Page<SysUser> page = new Page<>(current, size);
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(userName != null, SysUser::getUserName, userName)
                .like(email != null, SysUser::getEmail, email)
                .orderByDesc(SysUser::getCreateTime);

        Page<SysUser> result = sysUserService.page(page, wrapper);

        return R.success(PageResult.pageResult(result));
    }

    @ApiOperation("获取所有用户")
    @GetMapping("/list")
    public R<List<SysUser>> list() {
        List<SysUser> list = sysUserService.list();
        return R.success(list);
    }

    @ApiOperation("根据ID查询用户")
    @GetMapping("/{id}")
    public R<SysUser> getById(@ApiParam("用户ID") @PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        return R.success(user);
    }

    @ApiOperation("新增用户")
    @PostMapping
    public R<?> save(@Validated @RequestBody UserDTO userDTO) {
        try {
            Boolean result = sysUserService.saveUser(userDTO);
            return R.success(result);
        } catch (Exception e) {
            log.error("新增用户失败", e);
            return R.error(e.getMessage());
        }
    }

    @ApiOperation("更新用户")
    @PutMapping
    public R<Boolean> update(@Validated @RequestBody SysUser user) {
        boolean result = sysUserService.updateById(user);
        return R.success(result);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("用户ID") @PathVariable Long id) {
        boolean result = sysUserService.removeById(id);
        return R.success(result);
    }

    @ApiOperation("检查用户名是否存在")
    @GetMapping("/check-username/{userName}")
    public R<Boolean> checkUsername(@ApiParam("用户名") @PathVariable String userName) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, userName);
        boolean exists = sysUserService.count(wrapper) > 0;
        return R.success(exists);
    }

    @ApiOperation("检查邮箱是否存在")
    @GetMapping("/check-email/{email}")
    public R<Boolean> checkEmail(@ApiParam("邮箱") @PathVariable String email) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getEmail, email);
        boolean exists = sysUserService.count(wrapper) > 0;
        return R.success(exists);
    }
}
