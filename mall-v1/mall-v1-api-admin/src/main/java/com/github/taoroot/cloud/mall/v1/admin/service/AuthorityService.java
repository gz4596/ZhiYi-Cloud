package com.github.taoroot.cloud.mall.v1.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;

public interface AuthorityService extends IService<AdminAuthority> {

    R<IPage<AdminAuthority>> pageByMenu(Integer menuId, Page<AdminAuthority> page);
}
