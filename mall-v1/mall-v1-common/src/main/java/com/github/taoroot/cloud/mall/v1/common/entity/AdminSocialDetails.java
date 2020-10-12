package com.github.taoroot.cloud.mall.v1.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 社交登录账号表
 */
@Data
@TableName("admin_social_details")
@EqualsAndHashCode(callSuper = true)
public class AdminSocialDetails extends Model<AdminSocialDetails> {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Integer id;

	private String type;

	private String title;

	private String appId;

	private String appSecret;

	private String authorizeUri;

	private String icon;

	private String proxyUri;

	private Boolean isProxy;
}
