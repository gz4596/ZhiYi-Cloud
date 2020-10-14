package com.github.taoroot.cloud.mall.v1.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminMenu;

import java.util.List;

public interface MenuService extends IService<AdminMenu> {

    R<List<?>> getTree(String title, Boolean hidden);

    R<String> update(AdminMenu adminMenu);

    R<String> create(AdminMenu adminMenu);

    R<String> remove(Integer id);

    R<String> sort(Integer menuId, Integer offset);
}
