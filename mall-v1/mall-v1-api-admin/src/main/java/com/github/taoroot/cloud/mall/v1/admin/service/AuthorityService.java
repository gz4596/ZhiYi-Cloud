package com.github.taoroot.cloud.mall.v1.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;

import java.util.List;

public interface AuthorityService extends IService<AdminAuthority> {

    R<List<AdminAuthority>> pageByMenu(String menuId);
}
