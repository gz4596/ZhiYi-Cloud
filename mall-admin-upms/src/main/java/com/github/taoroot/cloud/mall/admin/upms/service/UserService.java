package com.github.taoroot.cloud.mall.admin.upms.service;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.admin.api.entity.UpmsUser;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<UpmsUser> {


    R getPage(Page<UpmsUser> page, String username, String phone, Integer deptId, Boolean enabled);

    R saveOrUpdateItem(UpmsUser upmsUser);


}
