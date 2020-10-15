package com.github.taoroot.cloud.mall.v1.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.admin.mapper.AuthorityMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.AuthorityService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminMenuAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, AdminAuthority> implements AuthorityService {

    @Override
    public R<IPage<AdminAuthority>> pageByMenu(Integer menuId, Page<AdminAuthority> page) {
        return R.ok(baseMapper.selectByMenu(
                page,
                Wrappers.<AdminMenuAuthority>lambdaQuery()
                        .eq(AdminMenuAuthority::getMenuId, menuId)
        ));
    }

}
