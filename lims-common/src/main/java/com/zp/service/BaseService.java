package com.zp.service;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * BaseService
 *
 * @author zhengpanone
 * @since 2021-11-25
 */
public class BaseService<T> {

    protected Specification<T> getSpec(String companyId){
        Specification<T> specification = new Specification() {
            /**
             * 用于构造查询条件
             * @param root 包含所有的对象数据
             * @param criteriaQuery
             * @param cb 构造查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                // 根据企业id查询
                return cb.equal(root.get("companyId").as(String.class),companyId);
            }

        };
        return specification;
    }

}
