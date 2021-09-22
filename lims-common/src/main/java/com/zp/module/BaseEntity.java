package com.zp.module;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import javax.persistence.Table;

/**
 * Base Entity
 * @author zhengpanone
 */
@Data
public class BaseEntity implements Serializable {
    private Date createTime;
    private String createUser;
    private Date lastModifiedTime;
    private String lastModifiedUser;

}
