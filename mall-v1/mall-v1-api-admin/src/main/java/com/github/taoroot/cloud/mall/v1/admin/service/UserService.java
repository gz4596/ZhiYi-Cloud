package com.github.taoroot.cloud.mall.v1.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;

public interface UserService extends IService<AdminUser> {


    R getPage(Page<AdminUser> page, String username, String phone, Integer deptId, Boolean enabled);

    R saveOrUpdateItem(AdminUser adminUser);
}
