package com.zp.lims.common.core.controller;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.common.constant.PageConst;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class BaseController {
    @Autowired
    protected  HttpServletRequest request;
    @Autowired
    protected  HttpServletResponse response;
    @Autowired
    protected  HttpSession session;

    protected <T> Page<T> getPage() {
        Integer start = Convert.toInt(request.getParameter(PageConst.page), PageConst.DEFAULT_PAGE);
        Integer limit = Convert.toInt(request.getParameter(PageConst.page), PageConst.DEFAULT_SIZE);
        limit = limit > PageConst.MAX_LIMIT ? PageConst.MAX_LIMIT : limit;
        Integer cursor = start / limit + 1;
        Page<T> page = new Page<>(cursor, limit, true);
        return page;
    }
}
