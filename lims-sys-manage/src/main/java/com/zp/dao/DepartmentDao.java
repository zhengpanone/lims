package com.zp.dao;

import com.zp.domain.sys.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * DepartmentDao 部门Dao
 *
 * @author zhengpanone
 * @since 2021-11-23
 */
public interface DepartmentDao extends JpaRepository<Department,String>, JpaSpecificationExecutor<Department> {
}
