package com.github.taoroot.cloud.mall.v1.admin.vo;

import lombok.Data;

/**
 * 统一用户信息格式
 */
@Data
public class UserInfoVO {
    private String nickname;
    private String username;
    private String[] function = new String[]{};
    private String[] menu = new String[]{};
}
