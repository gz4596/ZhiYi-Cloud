package com.github.taoroot.cloud.mall.v1.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminRole;

public interface RoleService extends IService<AdminRole> {

    R getPage(Page<AdminRole> page);

    R saveOrUpdateItem(AdminRole adminRole);
}
