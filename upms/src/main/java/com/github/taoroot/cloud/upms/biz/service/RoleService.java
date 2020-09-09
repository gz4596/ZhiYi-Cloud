package com.github.taoroot.cloud.upms.biz.service;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.upms.api.entity.UpmsRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RoleService extends IService<UpmsRole> {

    R getPage(Page<UpmsRole> page);

    R saveOrUpdateItem(UpmsRole upmsRole);
}
