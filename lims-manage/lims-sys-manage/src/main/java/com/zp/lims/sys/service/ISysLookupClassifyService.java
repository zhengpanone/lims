package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysLookupClassifyEntity;

/**
 * 数据字典分类服务接口
 *
 * @author zhengp
 */
public interface ISysLookupClassifyService extends IService<SysLookupClassifyEntity> {

    /**
     * 分页查询数据字典分类
     *
     * @param page 分页参数
     * @param classify 查询条件
     * @return 分页结果
     */
    IPage<SysLookupClassifyEntity> queryPage(Page<SysLookupClassifyEntity> page, SysLookupClassifyEntity classify);

    /**
     * 保存数据字典分类
     *
     * @param classify 数据字典分类
     * @return 是否成功
     */
    boolean saveClassify(SysLookupClassifyEntity classify);

    /**
     * 更新数据字典分类
     *
     * @param classify 数据字典分类
     * @return 是否成功
     */
    boolean updateClassify(SysLookupClassifyEntity classify);

    /**
     * 删除数据字典分类
     *
     * @param id 数据字典分类ID
     * @return 是否成功
     */
    boolean deleteClassify(Long id);

    /**
     * 批量删除数据字典分类
     *
     * @param ids 数据字典分类ID数组
     * @return 是否成功
     */
    boolean deleteBatchClassify(Long[] ids);
}
