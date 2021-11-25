package com.zp.module;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志类
 *
 * @author zhengpanone
 * @since 2021-11-17
 */
@Entity
@Table(name = "sys_log")
@Data
public class SysLogEntity implements Serializable {
    /**
     * Id
     */
    @Id
    private String id;
    /**
     * 用户操作
     */

    private String operation;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 执行时长
     */
    private String userTimeMs;
    /**
     * 操作用户
     */
    private String userName;
    /**
     * IP 地址
     */
    private String ip;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 响应code
     */
    private String responseCode;
    /**
     * 响应内容
     */
    private String responseMessage;
    /**
     * 浏览器头信息
     */
    private String userAgent;
}
