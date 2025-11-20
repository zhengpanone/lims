package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysLookupItemEntity;

/**
 * 数据字典项服务接口
 *
 * @author zhengp
 */
public interface ISysLookupItemService extends IService<SysLookupItemEntity> {

    /**
     * 分页查询数据字典项
     *
     * @param page 分页参数
     * @param lookupItem 查询条件
     * @return 分页结果
     */
    IPage<SysLookupItemEntity> queryPage(Page<SysLookupItemEntity> page, SysLookupItemEntity lookupItem);

    /**
     * 保存数据字典项
     *
     * @param lookupItem 数据字典项
     * @return 是否成功
     */
    boolean saveLookupItem(SysLookupItemEntity lookupItem);

    /**
     * 更新数据字典项
     *
     * @param lookupItem 数据字典项
     * @return 是否成功
     */
    boolean updateLookupItem(SysLookupItemEntity lookupItem);

    /**
     * 删除数据字典项
     *
     * @param id 数据字典项ID
     * @return 是否成功
     */
    boolean deleteLookupItem(Long id);

    /**
     * 批量删除数据字典项
     *
     * @param ids 数据字典项ID数组
     * @return 是否成功
     */
    boolean deleteBatchLookupItem(Long[] ids);
}
