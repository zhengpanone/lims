package com.zp.domain.sys;

import com.zp.module.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Company
 *
 * @author zhengpanone
 * @since 2021-09-22
 */
@Entity
@Table(name = "co_company")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Company extends BaseEntity {
    /**
     * ID
     */

    @Id
    private String id;
    /**
     * 公司名称
     */
    private String name;

    /**
     * 企业登录账号ID
     */

    private String managerId;
    /**
     * 当前版本
     */

    private String version;
    /**
     * 续期时间
     */

    private Date renewalDate;
    /**
     * 到期时间
     */

    private Date expirationDate;
    /**
     * 公司地区
     */

    private String companyArea;
    /**
     * 公司地址
     */

    private String companyAddress;
    /**
     * 营业执照-图片ID
     */

    private String businessLicenseId;

    /**
     * 法人代表
     */
    private String legalRepresentative;
    /**
     * 公司电话
     */
    private String companyPhone;
    /**
     * 邮箱
     */
    private String mailbox;
    /**
     * 公司规模
     */
    private String companySize;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 审核状态
     */
    private String auditState;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 当前余额
     */
    private Double balance;
    /**
     * 创建时间
     */
    private Date createTime;
}
