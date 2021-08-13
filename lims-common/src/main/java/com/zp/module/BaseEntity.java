package com.zp.module;

import java.util.Date;

import lombok.Data;

/**
 * Base Entity
 */
@Data
public class BaseEntity {

    private Date createTime;
    private String createUser;
    private Date lastModifiedTime;
    private String lastModifiedUser;

}
