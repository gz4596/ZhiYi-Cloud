package com.github.taoroot.cloud.mall.v1.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.common.core.datascope.DataScope;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUserRole;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserMapper;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserRoleMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.UserRoleService;
import com.github.taoroot.cloud.mall.v1.admin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, AdminUser> implements UserService {

    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;

    @Override
    public R getPage(Page<AdminUser> page, String username, String phone, Integer deptId, Boolean enabled) {
        IPage<AdminUser> result = userMapper.getPage(page, username, phone, deptId, enabled);
        return R.ok(result);
    }

    @Override
    public R saveOrUpdateItem(AdminUser adminUser) {
        // 更新用户信息
        adminUser.updateById();

        // 更新角色信息
        if (adminUser.getRoles() != null) {
            List<AdminUserRole> roleMenuList = Arrays.stream(adminUser.getRoles()).map(userId -> {
                AdminUserRole userRole = new AdminUserRole();
                userRole.setRoleId(userId);
                userRole.setUserId(adminUser.getId());
                return userRole;
            }).collect(Collectors.toList());
            userRoleMapper.delete(Wrappers.<AdminUserRole>query().lambda()
                    .eq(AdminUserRole::getUserId, adminUser.getId()));
            userRoleService.saveBatch(roleMenuList);
        }

        return R.ok();
    }

}
