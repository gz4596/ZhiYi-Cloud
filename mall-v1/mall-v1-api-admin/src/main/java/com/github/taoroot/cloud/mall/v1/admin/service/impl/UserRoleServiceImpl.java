package com.github.taoroot.cloud.mall.v1.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUserRole;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserRoleMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, AdminUserRole> implements UserRoleService {

}
