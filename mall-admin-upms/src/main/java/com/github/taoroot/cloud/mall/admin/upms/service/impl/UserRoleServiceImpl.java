package com.github.taoroot.cloud.mall.admin.upms.service.impl;


import com.github.taoroot.cloud.mall.admin.api.entity.UpmsUserRole;
import com.github.taoroot.cloud.mall.admin.upms.mapper.UserRoleMapper;
import com.github.taoroot.cloud.mall.admin.upms.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UpmsUserRole> implements UserRoleService {

}
