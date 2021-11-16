package com.zp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import com.zp.IdWorker;
import com.zp.dao.UserDao;
import com.zp.domain.sys.SysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/07 20:56.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;

    public Optional<SysUser> findByMobile(String mobile) {

        return userDao.findByMobile(mobile);
    }

    /**
     * 保存用户
     * 
     * @param sysUser
     */
    public void save(SysUser sysUser) {
        String id = idWorker.nextId() + "";
        sysUser.setPassword("123456");
        sysUser.setId(id);
        userDao.save(sysUser);
    }

    /**
     * 根据ID更新用户
     *
     * @param sysUser
     */
    public void update(SysUser sysUser) {
        SysUser target = userDao.findById(sysUser.getId()).get();
        target.setUsername(sysUser.getUsername());
        target.setDepartmentId(sysUser.getDepartmentId());
        target.setDepartmentName(sysUser.getDepartmentName());
        userDao.save(sysUser);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    public Optional<SysUser> findById(String id) {
        return userDao.findById(id);
    }

    /**
     * @param map
     * @return
     */
    public Page<SysUser> findAll(Map<String, Object> map, int page, int size) {
        Specification<SysUser> spec = new Specification<SysUser>() {
            /**
             * 动态拼接查询条件
             * 
             * @param root
             * @param criteriaQuery
             * @param criteriaBuilder
             * @return
             */
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                String companyId = "companyId";
                String department = "department";
                String hasDept = "hasDept";
                String zero = "0";
                List<Predicate> list = new ArrayList<>();
                if (!ObjectUtils.isEmpty(map.get(companyId))) {
                    list.add(criteriaBuilder.equal(root.get(companyId).as(String.class), map.get(companyId)));
                }
                // 是否分配部门 0未分配 1已分配
                if (ObjectUtils.isEmpty(map.get(hasDept)) || zero.equals(map.get(hasDept))) {
                    list.add(criteriaBuilder.isNull(root.get(department)));
                }
                if (!ObjectUtils.isEmpty(map.get(department))) {
                    list.add(criteriaBuilder.equal(root.get(department).as(String.class), map.get(department)));
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        Page<SysUser> pageUser = userDao.findAll(spec, PageRequest.of(page - 1, size));
        return pageUser;
    }

    public void deleteById(String id) {
        userDao.deleteById(id);
    }

}
