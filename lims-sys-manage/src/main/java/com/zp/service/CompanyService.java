package com.zp.service;

import com.zp.IdWorker;
import com.zp.dao.CompanyDao;
import com.zp.domain.sys.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * CompanyService
 *
 * @author zhengpanone
 * @since 2021-09-22
 */
@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加企业
     * @param company
     * @return
     */
    public Company add(Company company){
        company.setId(idWorker.nextId()+"");
        company.setCreateTime(new Date());
        company.setState(1);//启用
        company.setAuditState("0"); // 待审核
        company.setBalance(0d);
        companyDao.save(company);
        return company;
    }

    public Company update(Company company){
        return companyDao.save(company);
    }
    public Company findById(String id){
        Optional<Company> byId = companyDao.findById(id);
        return byId.orElse(null);
    }
    public void deleteById(String id){
        companyDao.deleteById(id);
    }
    public List<Company> findAll(){
        return companyDao.findAll();
    }
}
