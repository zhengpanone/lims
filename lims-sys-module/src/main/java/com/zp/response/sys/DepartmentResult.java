package com.zp.response.sys;

import com.zp.domain.sys.Company;
import com.zp.domain.sys.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DepartmentResult
 *
 * @author zhengpanone
 * @since 2021-11-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResult {
    private String companyId;
    private String companyName;
    private String companyManage;
    private List<Department> departments;

    public DepartmentResult (Company company , List<Department> departments){
        this.companyId = company.getId();
        this.companyName = company.getName();
        this.companyManage = company.getLegalRepresentative(); // 公司联系人
        this.departments = departments;
    }
}

