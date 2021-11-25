package com.zp.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/08 9:30.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String companyId;
    protected String companyName;

    @ModelAttribute
    public void setResAndReq(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.companyId = "1440616037694394368";
        this.companyName = "testCompany";

    }
}
