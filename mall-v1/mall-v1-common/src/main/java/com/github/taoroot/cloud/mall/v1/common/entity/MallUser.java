package com.github.taoroot.cloud.mall.v1.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商城用户
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("mp_user")
public class MallUser extends Model<MallUser> {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 微信唯一编码
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 1男 2女 0未知
     */
    private Integer sex;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 头像
     */
    private String headImgUrl;

    /**
     * 手机号
     */
    private String mobile;
}
