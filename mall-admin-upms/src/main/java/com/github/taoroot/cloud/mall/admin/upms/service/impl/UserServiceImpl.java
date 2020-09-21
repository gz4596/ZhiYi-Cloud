package com.github.taoroot.cloud.mall.admin.upms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.common.core.datascope.DataScope;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.mall.admin.api.entity.UpmsUser;
import com.github.taoroot.cloud.mall.admin.api.entity.UpmsUserRole;
import com.github.taoroot.cloud.mall.admin.upms.mapper.UserMapper;
import com.github.taoroot.cloud.mall.admin.upms.mapper.UserRoleMapper;
import com.github.taoroot.cloud.mall.admin.upms.service.UserRoleService;
import com.github.taoroot.cloud.mall.admin.upms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UpmsUser> implements UserService {

    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;

    @Override
    public R getPage(Page<UpmsUser> page, String username, String phone, Integer deptId, Boolean enabled) {
        DataScope dataScope = SecurityUtils.dataScope();
        dataScope.setScopeOwnName("id");
        IPage<UpmsUser> result = userMapper.getPage(page, dataScope, username, phone, deptId, enabled);
        return R.ok(result);
    }

    @Override
    public R saveOrUpdateItem(UpmsUser upmsUser) {
        // 更新用户信息
        upmsUser.updateById();

        // 更新角色信息
        if (upmsUser.getRoles() != null) {
            List<UpmsUserRole> roleMenuList = Arrays.stream(upmsUser.getRoles()).map(userId -> {
                UpmsUserRole userRole = new UpmsUserRole();
                userRole.setRoleId(userId);
                userRole.setUserId(upmsUser.getId());
                return userRole;
            }).collect(Collectors.toList());
            userRoleMapper.delete(Wrappers.<UpmsUserRole>query().lambda()
                    .eq(UpmsUserRole::getUserId, upmsUser.getId()));
            userRoleService.saveBatch(roleMenuList);
        }

        return R.ok();
    }

}
