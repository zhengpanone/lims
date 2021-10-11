package com.zp.controller;

import com.zp.api.sys.CompanyControllerApi;
import com.zp.domain.sys.Company;
import com.zp.response.ResponseResult;
import com.zp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CompanyController
 *
 * @author zhengpanone
 * @since 2021-09-22
 */
@CrossOrigin
@RestController
@RequestMapping("/sys")
public class CompanyController extends BaseController implements CompanyControllerApi {
    @Autowired
    private CompanyService companyService;
    @Override
    @PostMapping("/company")
    public ResponseResult save(@RequestBody Company company) {
        System.out.println(company);
        companyService.add(company);
        return ResponseResult.SUCCESS();
    }
}
