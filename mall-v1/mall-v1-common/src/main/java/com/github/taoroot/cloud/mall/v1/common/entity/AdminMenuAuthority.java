package com.github.taoroot.cloud.mall.v1.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("admin_menu_authority")
@EqualsAndHashCode(callSuper = true)
public class AdminMenuAuthority extends Model<AdminMenuAuthority> {

    private static final long serialVersionUID = 1L;

    private Integer menuId;

    private Integer authorityId;
}
