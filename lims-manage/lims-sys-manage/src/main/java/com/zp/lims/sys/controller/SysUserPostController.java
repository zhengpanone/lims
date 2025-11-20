package com.zp.lims.sys.controller;

import com.zp.lims.common.core.controller.BaseController;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.entity.SysUserPost;
import com.zp.lims.sys.service.ISysUserPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户岗位管理控制器
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@RestController
@Api(tags = "用户岗位管理")
@RequestMapping("/userPost")
@RequiredArgsConstructor
@Slf4j
public class SysUserPostController extends BaseController {
    
    private final ISysUserPostService sysUserPostService;

    @ApiOperation("根据用户ID查询岗位")
    @GetMapping("/user/{userId}")
    public R<List<SysUserPost>> getByUserId(@ApiParam("用户ID") @PathVariable Long userId) {
        List<SysUserPost> userPosts = sysUserPostService.getByUserId(userId);
        return R.success(userPosts);
    }

    @ApiOperation("根据岗位ID查询用户")
    @GetMapping("/post/{postId}")
    public R<List<SysUserPost>> getByPostId(@ApiParam("岗位ID") @PathVariable Long postId) {
        List<SysUserPost> userPosts = sysUserPostService.getByPostId(postId);
        return R.success(userPosts);
    }

    @ApiOperation("为用户分配岗位")
    @PostMapping("/assign/{userId}")
    public R<Boolean> assignPostsToUser(
            @ApiParam("用户ID") @PathVariable Long userId,
            @ApiParam("岗位ID列表") @RequestBody List<Long> postIds) {
        Boolean result = sysUserPostService.assignPostsToUser(userId, postIds);
        return R.success(result);
    }

    @ApiOperation("删除用户岗位关联")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("关联ID") @PathVariable Long id) {
        boolean result = sysUserPostService.removeById(id);
        return R.success(result);
    }
} 