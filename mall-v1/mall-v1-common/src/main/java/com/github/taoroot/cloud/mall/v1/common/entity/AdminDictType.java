package com.github.taoroot.cloud.mall.v1.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "admin_dict_type")
public class AdminDictType extends Model<AdminDictType> {
    /**
     * 字典主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}
