package com.github.taoroot.cloud.upms.biz.service;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.upms.api.entity.UpmsAuthority;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AuthorityService extends IService<UpmsAuthority> {
    R getTree(String title, Boolean hidden);
}
