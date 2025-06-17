-- 用户表，存储系统用户基本信息
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
    id          BIGINT PRIMARY KEY COMMENT '用户ID，主键',
    username    VARCHAR(64)  NOT NULL COMMENT '登录用户名，唯一',
    password    VARCHAR(128) NOT NULL COMMENT '密码（加密存储）',
    nickname    VARCHAR(64) COMMENT '用户昵称',
    email       VARCHAR(128) COMMENT '电子邮箱',
    phone       VARCHAR(20) COMMENT '联系电话',
    status      TINYINT  DEFAULT 1 COMMENT '用户状态，1=启用，0=禁用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT ='系统用户表';

-- 组织表（树形结构），支持多级组织架构
DROP TABLE IF EXISTS sys_org;
CREATE TABLE sys_org
(
    id          BIGINT PRIMARY KEY COMMENT '组织ID，主键',
    name        VARCHAR(100) NOT NULL COMMENT '组织名称',
    parent_id   BIGINT COMMENT '父级组织ID，顶级为NULL',
    org_code    VARCHAR(50) COMMENT '组织编码，用于唯一标识',
    level       INT COMMENT '组织层级，顶级为1',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间'
) COMMENT ='组织机构表';

-- 职位表，存储职位信息
DROP TABLE IF EXISTS sys_position;
CREATE TABLE sys_position
(
    id          BIGINT PRIMARY KEY COMMENT '职位ID，主键',
    name        VARCHAR(100) NOT NULL COMMENT '职位名称',
    code        VARCHAR(50) COMMENT '职位编码',
    description VARCHAR(255) COMMENT '职位描述'
) COMMENT ='职位信息表';

-- 用户组表，存储用户分组信息
DROP TABLE IF EXISTS sys_user_group;
CREATE TABLE sys_user_group
(
    id          BIGINT PRIMARY KEY COMMENT '用户组ID，主键',
    name        VARCHAR(100) NOT NULL COMMENT '用户组名称',
    description VARCHAR(255) COMMENT '用户组描述'
) COMMENT ='用户组表';

-- 角色表，存储角色信息及其数据权限范围
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role
(
    id          BIGINT PRIMARY KEY COMMENT '角色ID，主键',
    name        VARCHAR(100) NOT NULL COMMENT '角色名称',
    code        VARCHAR(50) UNIQUE COMMENT '角色唯一编码',
    description VARCHAR(255) COMMENT '角色描述',
    data_scope  VARCHAR(50) COMMENT '数据权限范围，如：ALL（全部）、DEPT（部门）、SELF（本人）',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间'
) COMMENT ='角色信息表';

-- 权限表，存储系统权限点（菜单、按钮、接口）
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission
(
    id          BIGINT PRIMARY KEY COMMENT '权限ID，主键',
    name        VARCHAR(100) NOT NULL COMMENT '权限名称',
    code        VARCHAR(100) NOT NULL COMMENT '权限唯一编码',
    type        VARCHAR(20) COMMENT '权限类型：menu（菜单）、button（按钮）、api（接口）',
    path        VARCHAR(255) COMMENT '权限路径（如URL或接口路径）',
    method      VARCHAR(10) COMMENT '请求方法（GET, POST等）',
    parent_id   BIGINT COMMENT '父级权限ID，构建权限树',
    sort        INT COMMENT '排序字段',
    description VARCHAR(255) COMMENT '权限描述'
) COMMENT ='权限表';

-- 用户-组织关联表，多对多关系
DROP TABLE IF EXISTS sys_user_org;
CREATE TABLE sys_user_org
(
    user_id BIGINT COMMENT '用户ID',
    org_id  BIGINT COMMENT '组织ID',
    PRIMARY KEY (user_id, org_id)
) COMMENT ='用户与组织关联表';

-- 用户-职位关联表，建议加 org_id 区分同职位不同部门
DROP TABLE IF EXISTS sys_user_position;
CREATE TABLE sys_user_position
(
    user_id     BIGINT COMMENT '用户ID',
    position_id BIGINT COMMENT '职位ID',
    org_id      BIGINT COMMENT '组织ID',
    PRIMARY KEY (user_id, position_id, org_id)
) COMMENT ='用户与职位关联表';

-- 用户-用户组关联表，多对多关系
DROP TABLE IF EXISTS sys_user_user_group;
CREATE TABLE sys_user_user_group
(
    user_id       BIGINT COMMENT '用户ID',
    user_group_id BIGINT COMMENT '用户组ID',
    PRIMARY KEY (user_id, user_group_id)
) COMMENT ='用户与用户组关联表';

-- 用户-角色关联表，用户直接分配的角色
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role
(
    user_id BIGINT COMMENT '用户ID',
    role_id BIGINT COMMENT '角色ID',
    PRIMARY KEY (user_id, role_id)
) COMMENT ='用户与角色关联表';

-- 组织-角色关联表，组织分配的角色
DROP TABLE IF EXISTS sys_org_role;
CREATE TABLE sys_org_role
(
    org_id  BIGINT COMMENT '组织ID',
    role_id BIGINT COMMENT '角色ID',
    PRIMARY KEY (org_id, role_id)
) COMMENT ='组织与角色关联表';

-- 职位-角色关联表，职位分配的角色
DROP TABLE IF EXISTS sys_position_role;
CREATE TABLE sys_position_role
(
    position_id BIGINT COMMENT '职位ID',
    role_id     BIGINT COMMENT '角色ID',
    PRIMARY KEY (position_id, role_id)
) COMMENT ='职位与角色关联表';

-- 用户组-角色关联表，用户组分配的角色
DROP TABLE IF EXISTS sys_user_group_role;
CREATE TABLE sys_user_group_role
(
    user_group_id BIGINT COMMENT '用户组ID',
    role_id       BIGINT COMMENT '角色ID',
    PRIMARY KEY (user_group_id, role_id)
) COMMENT ='用户组与角色关联表';

-- 角色-权限关联表，角色所拥有的权限
DROP TABLE IF EXISTS sys_role_permission;
CREATE TABLE sys_role_permission
(
    role_id       BIGINT COMMENT '角色ID',
    permission_id BIGINT COMMENT '权限ID',
    PRIMARY KEY (role_id, permission_id)
) COMMENT ='角色与权限关联表';

-- 角色互斥表，用于配置角色间的互斥关系，避免用户拥有冲突角色
DROP TABLE IF EXISTS sys_role_mutex;
CREATE TABLE sys_role_mutex
(
    role_id       BIGINT COMMENT '角色ID',
    mutex_role_id BIGINT COMMENT '互斥角色ID',
    PRIMARY KEY (role_id, mutex_role_id)
) COMMENT ='角色互斥关系表';


DROP TABLE IF EXISTS sys_i18n;
create table sys_i18n
(
    i18n_id     int          null,
    module_type int          null,
    lang_type   varchar(255) null,
    `key`       varchar(255) null,
    value       varchar(255) null
);


drop table if exists sys_role;
create table sys_role
(
    id           varchar(32)  not null,
    create_user  varchar(255) not null comment '创建人',
    created_time timestamp    not null default now() comment '创建时间',
    update_user  varchar(255) null comment '更新人',
    update_time  timestamp    not null default now() comment '更新时间'

);


