package com.zp.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zp.module.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/07 17:19.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Entity
@Table(name = "sys_role")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity  {
    @Id
    private String id;
    /**
     * 角色名
     */
    @JoinColumn(name = "role_name")
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 企业id
     */
    private String companyId;
    /**
     * 角色于用户
     * 多对多
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "roles") //不维护中间表
    private Set<User> users = new HashSet<>(0);
    /**
     * 角色和模块 多对多
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id",
                    referencedColumnName = "id")})
    private Set<Permission> permissions = new HashSet<>(0);
}
