package com.zp.dao;

import com.zp.domain.sys.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * CompanyDao
 * 企业数据访问接口
 * @author zhengpanone
 * @since 2021-09-22
 */
public interface CompanyDao extends JpaRepository<Company,String>, JpaSpecificationExecutor<Company> {
}
