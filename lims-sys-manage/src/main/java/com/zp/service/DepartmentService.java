package com.zp.service;

import com.zp.IdWorker;
import com.zp.dao.DepartmentDao;
import com.zp.domain.sys.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * DepartmentService
 *
 * @author zhengpanone
 * @since 2021-11-23
 */
@Service
public class DepartmentService extends BaseService {
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 保存部门
     */
    public void save(Department department){
        department.setId(idWorker.nextId()+"");
        departmentDao.save(department);
    }

    /**
     * 更新部门
     */
    public void update(Department department){
        // 根据id查询
        Department dept = departmentDao.findById(department.getId()).get();
        // 设置修改属性
        dept.setCode(department.getCode());
        dept.setCompanyId(department.getCompanyId());
        dept.setName(department.getName());
        departmentDao.save(dept);

    }
    /**
     * 根据ID查询部门
     */
    public Department findById(String id){
        return departmentDao.findById(id).get();
    }

    /**
     * 查询全部部门列表
     */
    public List<Department> findAll(String companyId){
        return departmentDao.findAll(getSpec(companyId));
    }
    /**
     * 根据ID删除部门
     */
    public void deleteById(String id){
        departmentDao.deleteById(id);
    }
}
