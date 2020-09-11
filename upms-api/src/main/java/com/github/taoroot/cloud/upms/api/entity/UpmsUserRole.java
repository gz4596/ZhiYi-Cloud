package com.github.taoroot.cloud.upms.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@TableName("upms_user_role")
@EqualsAndHashCode(callSuper = true)
public class UpmsUserRole extends Model<UpmsUserRole> {

    private static final long serialVersionUID = 1L;

    private String userId;

    private Integer roleId;
}
