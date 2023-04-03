package com.zp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class SysMenuEntity extends BaseEntity {
    @TableId
    private String menuId;

    private String parentId;
    private String parentName;
    private String icon;
    private String name;
    private String url;

    private String perms;
    private Integer menuIndex;
    /**
     * 类型： 0目录、1菜单、2按钮
     */
    private Integer menuType;



}
