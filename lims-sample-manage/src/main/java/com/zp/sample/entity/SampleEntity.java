package com.zp.sample.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("m_sample")
public class SampleEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @TableId(value = "sample_id", type = IdType.ASSIGN_ID)
    private Long sampleId;
    /**
     * 样本编码
     */
    private String sampleCode;
}
