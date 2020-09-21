package com.github.taoroot.cloud.mall.admin.upms.service;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.admin.api.entity.UpmsAuthority;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AuthorityService extends IService<UpmsAuthority> {
    R getTree(String title, Boolean hidden);
}
