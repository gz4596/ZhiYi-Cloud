package com.github.taoroot.cloud.mall.v1.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminMenuAuthority;

import java.util.List;

public interface MenuAuthorityService extends IService<AdminMenuAuthority> {

    R<String> addAuthorityByMenu(Integer menuId, List<Integer> ids);

    R<String> removeAuthorityByMenu(Integer menuId, List<Integer> ids);
}
