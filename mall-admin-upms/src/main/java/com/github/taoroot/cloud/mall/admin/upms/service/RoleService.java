package com.github.taoroot.cloud.mall.admin.upms.service;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.admin.api.entity.UpmsRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RoleService extends IService<UpmsRole> {

    R getPage(Page<UpmsRole> page);

    R saveOrUpdateItem(UpmsRole upmsRole);
}
