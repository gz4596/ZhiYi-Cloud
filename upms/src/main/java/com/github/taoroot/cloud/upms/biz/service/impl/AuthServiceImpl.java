package com.github.taoroot.cloud.upms.biz.service.impl;

import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.utils.TreeUtils;
import com.github.taoroot.cloud.common.core.vo.AuthUserInfo;
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
        HashMap<String, Object> result = new HashMap<>();
        return R.ok(result);
    }

    @Override
    public AuthUserInfo authByUsername(String username) {
        UpmsUser upmsUser = userMapper.selectOne(Wrappers.<UpmsUser>lambdaQuery().eq(UpmsUser::getUsername, username));

        if (upmsUser == null) {
            return null;
        }
        String userId = upmsUser.getId();

        AuthUserInfo userInfo = new AuthUserInfo();
        userInfo.setUsername(upmsUser.getId());
        userInfo.setPassword(upmsUser.getPassword());
        userInfo.setAuthorities(userMapper.roleNames(userId).toArray(new String[0]));
        List<UpmsAuthority> menus = userMapper.authorities(userId, 0);

        userInfo.getAttrs().put("menus", TreeUtil.build(menus, TreeUtils.ROOT_PARENT_ID, (treeNode, tree) -> {
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

        userInfo.setRoleIds(userMapper.roleIds(userId));
        return userInfo;
    }
}
