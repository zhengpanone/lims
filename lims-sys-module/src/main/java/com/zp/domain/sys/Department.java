package com.zp.domain.sys;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zp.module.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/07 17:19.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Entity
@Table(name = "co_department")
@Data
public class Department extends BaseEntity implements Serializable {
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 父级ID
     */
    private String pid;

    /**
     * 企业ID
     */
    private String companyId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门编码,同级部门不可重复
     */
    private String code;
    /**
     * 负责人ID
     */
    private String managerId;
    /**
     * 负责人名称
     */
    private String manager;
    /**
     * 介绍
     */
    private String introduce;
    /**
     * 创建时间
     */
    private Date createTime;
}
