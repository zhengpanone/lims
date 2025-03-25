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
    update_user  varchar(255)  null comment '更新人',
    update_time timestamp    not null default now() comment '更新时间'

);


