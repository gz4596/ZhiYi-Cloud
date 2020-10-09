package com.github.taoroot.cloud.mall.v1.admin.component;

import com.github.taoroot.cloud.common.core.constant.SecurityConstants;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserMapper;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component(SecurityConstants.REAL_TIME_USER_DETAILS_SERVICE)
public class AuthUserService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser adminUser = userMapper.selectById(username);

        if (adminUser == null) {
            return null;
        }

        AuthUserInfo userInfo = new AuthUserInfo();
        userInfo.setUsername(username);
        // 角色
        userInfo.setRoleIds(userMapper.roleIds(adminUser.getId()));
        // 权限
        List<String> list = userMapper.authorityNames(adminUser.getId(), 1);
        list.addAll(userMapper.roleNames(adminUser.getId()));
        userInfo.setAuthorities(list.toArray(new String[0]));
        return SecurityUtils.getAuthUser(userInfo);
    }
}
