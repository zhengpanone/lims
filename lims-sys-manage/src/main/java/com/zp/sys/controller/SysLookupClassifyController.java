package com.zp.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.base.controller.BaseController;
import com.zp.response.PageResult;
import com.zp.response.R;
import com.zp.sys.entity.SysLookupClassifyEntity;
import com.zp.sys.service.ISysLookupClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "数据字典模块")
@RequestMapping("/lookupClassify")
@RequiredArgsConstructor
@Slf4j
public class SysLookupClassifyController extends BaseController {
    private final ISysLookupClassifyService sysLookupClassifyService;

    @ApiOperation(value = "查看所有字典类别")
    @GetMapping("/list")
    public R<?> list() {
        List<SysLookupClassifyEntity> list = sysLookupClassifyService.list();
        return R.success(list);
    }

    @ApiOperation(value = "分页查看字典类别")
    @GetMapping("/page")
    public R<?> page() {
        Page<SysLookupClassifyEntity> page = getPage();
        Page<SysLookupClassifyEntity> pageInfo = sysLookupClassifyService.page(page);
        return R.success(PageResult.pageResult(pageInfo));

    }
}
