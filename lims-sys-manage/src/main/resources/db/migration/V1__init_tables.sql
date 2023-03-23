DROP TABLE IF EXISTS sys_i18n;
create table sys_i18n
(
    i18n_id     int          null,
    module_type int          null,
    lang_type   varchar(255) null,
    `key`       varchar(255) null,
    value       varchar(255) null
);

/*ALTER TABLE sys_user_role ADD create_user varchar(255) NULL;
ALTER TABLE sys_user_role ADD create_time datetime NULL;
ALTER TABLE sys_user_role ADD update_user varchar(255) NULL;
ALTER TABLE sys_user_role ADD update_time datetime NULL;
alter table sys_user_role modify create_user varchar(255) null comment '创建人';
alter table sys_user_role modify create_time datetime null comment '创建时间';
alter table sys_user_role modify update_user varchar(255) null comment  '更新人';
alter table sys_user_role modify update_time datetime null comment  '更新时间';*/


