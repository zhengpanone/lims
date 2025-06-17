package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysI18nEntity;

/**
 * 国际化信息服务接口
 *
 * @author zhengp
 */
public interface ISysI18nService extends IService<SysI18nEntity> {

    /**
     * 分页查询国际化信息
     *
     * @param page 分页参数
     * @param i18n 查询条件
     * @return 分页结果
     */
    IPage<SysI18nEntity> queryPage(Page<SysI18nEntity> page, SysI18nEntity i18n);

    /**
     * 保存国际化信息
     *
     * @param i18n 国际化信息
     * @return 是否成功
     */
    boolean saveI18n(SysI18nEntity i18n);

    /**
     * 更新国际化信息
     *
     * @param i18n 国际化信息
     * @return 是否成功
     */
    boolean updateI18n(SysI18nEntity i18n);

    /**
     * 删除国际化信息
     *
     * @param id 国际化信息ID
     * @return 是否成功
     */
    boolean deleteI18n(Long id);

    /**
     * 批量删除国际化信息
     *
     * @param ids 国际化信息ID数组
     * @return 是否成功
     */
    boolean deleteBatchI18n(Long[] ids);
}
