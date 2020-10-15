package com.github.taoroot.cloud.mall.v1.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("admin_authority")
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminAuthority extends Model<AdminAuthority> {

    public static final int MENU = 0;
    public static final int FUNCTION = 1;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String method;

    private String path;

    private String authority;
}
