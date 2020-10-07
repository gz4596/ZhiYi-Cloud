package com.github.taoroot.cloud.mall.v1.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.mall.v1.common.entity.MallUser;
import com.github.taoroot.cloud.mall.v1.user.mapper.UserMapper;
import com.github.taoroot.cloud.mall.v1.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, MallUser> implements UserService {

    private final UserMapper wechatUserMapper;

    public R userInfo() {
        return R.ok(wechatUserMapper.selectById(SecurityUtils.userId()));
    }
}
