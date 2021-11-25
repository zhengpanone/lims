package com.zp.domain.sys;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zp.module.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/07 17:19.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Entity
@Table(name = "co_department")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity  {
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 企业ID
     */
    private String companyId;
    /**
     * 父级部门IDp
     */
    private String pid;

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
