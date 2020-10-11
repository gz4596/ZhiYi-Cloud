package com.github.taoroot.cloud.mall.v1.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserSocialMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.UserSocialService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUserSocial;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSocialServiceImpl extends ServiceImpl<UserSocialMapper, AdminUserSocial> implements UserSocialService {

}
