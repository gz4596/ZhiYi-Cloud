package com.github.taoroot.cloud.upms.biz.service.impl;


import com.github.taoroot.cloud.upms.api.entity.UpmsUserRole;
import com.github.taoroot.cloud.upms.biz.mapper.UserRoleMapper;
import com.github.taoroot.cloud.upms.biz.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UpmsUserRole> implements UserRoleService {

}
