package com.zp.controller;

import com.zp.api.sys.DepartmentControllerApi;
import com.zp.domain.sys.Company;
import com.zp.domain.sys.Department;
import com.zp.response.*;
import com.zp.response.sys.DepartmentResult;
import com.zp.service.CompanyService;
import com.zp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * DepartmentController
 *
 * @author zhengpanone
 * @since 2021-11-23
 */
@CrossOrigin
@RestController
@RequestMapping("/company")
public class DepartmentController extends BaseController implements DepartmentControllerApi {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CompanyService companyService;

    @PostMapping("/department")
    @Override
    public Result save(@RequestBody Department department) {
        department.setCompanyId("1440616037694394368");
        department.setName("save department");
        departmentService.save(department);
        return Result.SUCCESS();
    }
    @GetMapping("/department")
    @Override
    public Result findAll() {
        String companyId = this.companyId;
        Company company = companyService.findById(companyId);
        List<Department> list = departmentService.findAll(companyId);
        DepartmentResult departmentResult = new DepartmentResult(company, list);
        return new Result(ResultCode.SUCCESS, departmentResult);

    }

    @Override
    @GetMapping("/department/{id}")
    public Result findById(@PathVariable("id") String id) {
        Department department = departmentService.findById(id);
        return new Result(ResultCode.SUCCESS, department);
    }

    @Override
    @PutMapping("/department/{id}")
    public Result update(@PathVariable("id") String id, @RequestBody Department department) {
        department.setId(id);
        departmentService.update(department);
        return Result.SUCCESS();
    }
    @Override
    @DeleteMapping("/department/{id}")
    public Result delete(@PathVariable("id") String id){
        departmentService.deleteById(id);
        return Result.SUCCESS();
    }

}
