package com.zp.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.base.controller.BaseController;
import com.zp.response.PageResult;
import com.zp.response.R;
import com.zp.sys.entity.SysI18nEntity;
import com.zp.sys.service.ISysI18nService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "数据字典")
@RequestMapping("/i18n")
@RequiredArgsConstructor
public class SysI18nController extends BaseController {
    private final ISysI18nService sysI18nService;

    @ApiOperation(value = "分页查看数据字典")
    @PostMapping("/queryI18nPage")
    public R<PageResult<SysI18nEntity>> queryI18nPage(SysI18nEntity i18nEntity) {
        Page<SysI18nEntity> page = getPage();
        IPage<SysI18nEntity> pageInfo = sysI18nService.queryI18nPage(page, i18nEntity);
        return R.success(PageResult.pageResult(pageInfo));
    }
}
