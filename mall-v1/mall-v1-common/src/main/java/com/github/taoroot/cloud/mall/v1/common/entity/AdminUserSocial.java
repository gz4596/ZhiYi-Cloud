package com.github.taoroot.cloud.mall.v1.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("admin_user_social")
@EqualsAndHashCode(callSuper = true)
public class AdminUserSocial extends Model<AdminUserSocial> {
    private Integer id;

    private Integer adminUserId;

    private String socialType;

    private String socialId;

    private String socialNickname;

    private String socialAvatar;
}
