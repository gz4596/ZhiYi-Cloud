package com.github.taoroot.cloud.mall.v1.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;

public interface AuthorityService extends IService<AdminAuthority> {
    R getTree(String title, Boolean hidden);
}
