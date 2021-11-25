package com.zp.controller;

import com.zp.api.sys.CompanyControllerApi;
import com.zp.domain.sys.Company;
import com.zp.domain.sys.Department;
import com.zp.response.Result;
import com.zp.response.ResultCode;
import com.zp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CompanyController
 *
 * @author zhengpanone
 * @since 2021-09-22
 */
@CrossOrigin
@RestController
@RequestMapping("/")
public class CompanyController extends BaseController implements CompanyControllerApi {
    @Autowired
    private CompanyService companyService;

    @Override
    @PostMapping("/company")
    public Result save(@RequestBody Company company) {
        System.out.println(company);
        companyService.add(company);
        return new Result(ResultCode.SUCCESS);
    }
    @Override
    @GetMapping("/company")
    public Result findAll() {
        List<Company> companies = companyService.findAll();
        return new Result(ResultCode.SUCCESS, companies);
    }
    @Override
    @GetMapping("/company/{id}")
    public Result findById(@PathVariable("id")String id){
        Company company = companyService.findById(id);
        return new Result(ResultCode.SUCCESS, company);
    }

    @Override
    @PutMapping("/company/{id}")
    public Result update(@PathVariable("id") String id, Company company) {
        return null;
    }

    @Override
    @DeleteMapping("/company/{id}")
    public Result delete(@PathVariable("id") String id) {
        return null;
    }

}
