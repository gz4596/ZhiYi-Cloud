package com.github.taoroot.cloud.mall.v1.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.admin.mapper.AuthorityMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.AuthorityService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminMenuAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, AdminAuthority> implements AuthorityService {

    @Override
    public R<List<AdminAuthority>> pageByMenu(String menuId) {
        return R.ok(baseMapper.selectByMenu(
                Wrappers.<AdminMenuAuthority>lambdaQuery()
                        .eq(AdminMenuAuthority::getMenuId, menuId)
        ));
    }

}
