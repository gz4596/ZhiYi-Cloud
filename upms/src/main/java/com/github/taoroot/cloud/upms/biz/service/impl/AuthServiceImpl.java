package com.github.taoroot.cloud.upms.biz.service.impl;

import cn.hutool.core.lang.tree.TreeUtil;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.utils.TreeUtils;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.upms.api.entity.UpmsAuthority;
import com.github.taoroot.cloud.upms.api.entity.UpmsUser;
import com.github.taoroot.cloud.upms.biz.mapper.DeptMapper;
import com.github.taoroot.cloud.upms.biz.mapper.UserMapper;
import com.github.taoroot.cloud.upms.biz.mapper.UserOauth2Mapper;
import com.github.taoroot.cloud.upms.biz.mapper.UserRoleMapper;
import com.github.taoroot.cloud.upms.biz.service.AuthService;
import com.github.taoroot.cloud.upms.biz.service.UserRoleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Data
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;
    private final DeptMapper deptMapper;
    private final UserOauth2Mapper userOauth2Mapper;

    @Override
    public R userInfo() {
        Integer userId = SecurityUtils.userId();
        HashMap<String, Object> result = new HashMap<>();
        UpmsUser upmsUser = userMapper.selectById(SecurityUtils.userId());
        // 查询用户个人信息
        result.put("info", upmsUser);
        // 查询用户角色信息
        result.put("roles", userMapper.roles(userId));
        // 所属部门
        result.put("dept", deptMapper.selectById(upmsUser.getDeptId()).getName());
        // 功能: 1
        result.put("functions", userMapper.authorities(userId, 1));
        // 菜单: 0
        List<UpmsAuthority> menus = userMapper.authorities(userId, 0);
        result.put("menus", TreeUtil.build(menus, TreeUtils.ROOT_PARENT_ID, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getWeight());
            tree.setName(treeNode.getName());
            tree.putExtra("path", treeNode.getPath());
            tree.putExtra("hidden", treeNode.getHidden());
            tree.putExtra("alwaysShow", treeNode.getAlwaysShow());
            tree.putExtra("redirect", treeNode.getRedirect());
            tree.putExtra("type", treeNode.getType());
            tree.put("component", treeNode.getComponent());
            HashMap<String, Object> meta = new HashMap<>();
            meta.put("title", treeNode.getTitle());
            meta.put("icon", treeNode.getIcon());
            meta.put("breadcrumb", treeNode.getBreadcrumb());
            tree.putExtra("meta", meta);
        }));
        return R.ok(result);
    }

    @Override
    public AuthUserInfo authByUsername(String username) {
        AuthUserInfo userInfo = new AuthUserInfo();
        userInfo.setUserId("1");
        userInfo.setAuthorities(new String[]{"USER"});
        userInfo.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password"));
        return userInfo;
    }
}
