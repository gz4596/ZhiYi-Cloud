package com.github.taoroot.cloud.mall.v1.admin.service.impl;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.common.core.datascope.DataScopeTypeEnum;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.SecurityUtils;
import com.github.taoroot.cloud.mall.v1.admin.mapper.RoleAuthorityMapper;
import com.github.taoroot.cloud.mall.v1.admin.mapper.RoleMapper;
import com.github.taoroot.cloud.mall.v1.admin.mapper.UserMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.DeptService;
import com.github.taoroot.cloud.mall.v1.admin.service.RoleMenuService;
import com.github.taoroot.cloud.mall.v1.admin.service.RoleService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminRole;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminRoleMenu;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, AdminRole> implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleMenuService roleMenuService;
    private final RoleAuthorityMapper roleAuthorityMapper;
    private final UserMapper userMapper;
    private final DeptService deptService;

    @Override
    public R getPage(Page<AdminRole> page) {
        return R.ok(roleMapper.getPage(page));
    }

    @Override
    public R saveOrUpdateItem(AdminRole adminRole) {
        List<Integer> deptIds = new ArrayList<>();
        AdminUser adminUser = userMapper.selectById(SecurityUtils.userId());

        // 全部
        if (adminRole.getScopeType().equals(DataScopeTypeEnum.ALL.getValue())) {
            adminRole.setScope(new Integer[]{});
            adminRole.setScope(deptIds.toArray(new Integer[0]));
        }

        // 本级
        if (adminRole.getScopeType().equals(DataScopeTypeEnum.THIS_LEVEL.getValue())) {
            deptIds.add(adminUser.getDeptId());
            adminRole.setScope(deptIds.toArray(new Integer[0]));
        }

        // 本级, 下级
        if (adminRole.getScopeType().equals(DataScopeTypeEnum.THIS_LEVEL_CHILDREN.getValue())) {
            deptIds.add(adminUser.getDeptId()); // 本级
            List<Tree<Integer>> tree = deptService.tree(adminUser.getDeptId()); // 下级
            treeToList(deptIds, tree);
            adminRole.setScope(deptIds.toArray(new Integer[0]));
        }

        // 自定义不能空
        if (adminRole.getScopeType().equals(DataScopeTypeEnum.CUSTOMIZE.getValue())) {
            if (adminRole.getScope() == null || adminRole.getScope().length == 0) {
                throw new RuntimeException("自定义权限范围时,必须至少包含一个范围");
            }
        }

        // 用户级别,无部门id
       if (adminRole.getScopeType().equals(DataScopeTypeEnum.CUSTOMIZE.getValue())) {
           deptIds.clear();
        }


        saveOrUpdate(adminRole);

        // todo 更新角色权限
        if (adminRole.getMenus() != null) {
            List<AdminRoleMenu> roleMenuList = Arrays.stream(adminRole.getMenus()).map(menuId -> {
                AdminRoleMenu roleMenu = new AdminRoleMenu();
                roleMenu.setRoleId(adminRole.getId());
                roleMenu.setMenuId(menuId);
                return roleMenu;
            }).collect(Collectors.toList());

            roleAuthorityMapper.delete(Wrappers.<AdminRoleMenu>query().lambda()
                    .eq(AdminRoleMenu::getRoleId, adminRole.getId()));
            roleMenuService.saveBatch(roleMenuList);
        }

        return R.ok();
    }

    private void treeToList(List<Integer> list, List<Tree<Integer>> tree) {
        for (Tree<Integer> node : tree) {
            list.add(node.getId());
            if (node.getChildren() != null && node.getChildren().size() > 0) {
                treeToList(list, node.getChildren());
            }
        }
    }
}
