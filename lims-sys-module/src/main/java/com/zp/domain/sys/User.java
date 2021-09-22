package com.zp.domain.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zp.module.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/07 11:22.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@ApiModel("用户")
@Entity
@Table(name = "sys_user")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity  {
    /**
     * 用户ID
     */
    @ApiModelProperty("id")
    @Id
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String mobile;
    /**
     * 启用状态 0禁用 1启用
     */
    @ApiModelProperty("启用状态 0禁用 1启用")
    private Integer enableState;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 公司ID
     */
    private String companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 部门ID
     */
    private String departmentId;
    /**
     * 入职时间
     */
    private Date timeOfEntry;
    /**
     * 聘用形式
     */
    private Integer formOfEmployment;
    /**
     * 工号
     */
    private String workNumber;
    /**
     * 管理形式
     */
    private String formOfManagement;
    /**
     * 工作城市
     */
    private String workingCity;
    /**
     * 转正时间
     */
    private Date correctionTime;
    /**
     * 是否在职
     */
    private Integer inServiceStatus;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 用户与角色 多对多
     */
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();
}
