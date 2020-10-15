package com.github.taoroot.cloud.mall.v1.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("admin_user_role")
@EqualsAndHashCode(callSuper = true)
public class AdminUserRole extends Model<AdminUserRole> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer roleId;
}
