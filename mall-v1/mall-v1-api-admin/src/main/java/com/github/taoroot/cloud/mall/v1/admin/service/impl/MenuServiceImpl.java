package com.github.taoroot.cloud.mall.v1.admin.service.impl;


import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.utils.TreeUtils;
import com.github.taoroot.cloud.mall.v1.admin.mapper.MenuMapper;
import com.github.taoroot.cloud.mall.v1.admin.mapper.RoleAuthorityMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.MenuAuthorityService;
import com.github.taoroot.cloud.mall.v1.admin.service.MenuService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminMenu;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminRoleMenu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
@AllArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, AdminMenu> implements MenuService {

    private final MenuMapper menuMapper;
    private final RoleAuthorityMapper roleAuthorityMapper;
    private final MenuAuthorityService menuAuthorityService;

    @Override
    public R<List<?>> getTree(String title, Boolean hidden) {
        LambdaQueryWrapper<AdminMenu> query = Wrappers.lambdaQuery();

        if (hidden != null) {
            query.eq(AdminMenu::getHidden, hidden);
        }

        if (!StringUtils.isEmpty(title)) {
            query.like(AdminMenu::getTitle, title);
        }

        List<AdminMenu> menuList = menuMapper.selectList(query);

        List<Tree<Integer>> authorityTree = TreeUtil.build(menuList, TreeUtils.ROOT_PARENT_ID, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getWeight());
            tree.setName(treeNode.getName());
            tree.putExtra("path", treeNode.getPath());
            tree.putExtra("type", treeNode.getType());
            tree.putExtra("component", treeNode.getComponent());
            tree.putExtra("hidden", treeNode.getHidden());
            tree.putExtra("alwaysShow", treeNode.getAlwaysShow());
            tree.putExtra("redirect", treeNode.getRedirect());
            tree.putExtra("title", treeNode.getTitle());
            tree.putExtra("icon", treeNode.getIcon());
            tree.putExtra("authority", treeNode.getAuthority());
            tree.putExtra("breadcrumb", treeNode.getBreadcrumb());
        });

        if (authorityTree.size() == 0) {
            return R.ok(menuList);
        }

        // 排序
        TreeUtils.computeSort(authorityTree);

        // 计算路径
        computePath(authorityTree);

        return R.ok(authorityTree);
    }


    public static void computePath(List<? extends Tree<Integer>> trees) {
        if (trees == null || trees.size() == 0) {
            return;
        }
        for (Tree<Integer> parent : trees) {
            if (parent.getChildren() == null) {
                return;
            }
            parent.putExtra("absPath", parent.get("path"));
            for (Tree<Integer> child : parent.getChildren()) {
                String path1 = (String) parent.get("path");
                String path2 = (String) child.get("path");
                if (!StrUtil.startWithAny(path2, "http", "https")) {
                    child.putExtra("absPath", path1 + "/" + path2);
                } else {
                    child.putExtra("absPath", path2);
                }
            }
            computePath(parent.getChildren());
        }
    }


    @Override
    public R<String> remove(Integer id) {
        Integer count = roleAuthorityMapper.selectCount(Wrappers.<AdminRoleMenu>lambdaQuery()
                .eq(AdminRoleMenu::getMenuId, id));
        if (count > 0) {
            throw new RuntimeException("资源被角色绑定,请先解绑");
        }

        Assert.isTrue(removeById(id), "创建失败");

        return R.okMsg("删除失败");
    }

    @Override
    @Transactional
    public R<String> sort(Integer menuId, Integer index) {
        AdminMenu adminMenu = getById(menuId);
        Assert.notNull(adminMenu, "菜单不存在");

        List<AdminMenu> list = list(Wrappers.<AdminMenu>lambdaQuery()
                .eq(AdminMenu::getParentId, adminMenu.getParentId())
                .ne(AdminMenu::getId, adminMenu.getId())
                .orderByAsc(AdminMenu::getWeight));

        list.add(index, adminMenu);

        // 同一级重新排序
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWeight(i);
        }
        Assert.isTrue(updateBatchById(list), "创建失败");

        return R.okMsg("创建成功");
    }


    @Transactional
    @Override
    public R<String> update(AdminMenu menuPut) {
        Assert.isTrue(!menuPut.getParentId().equals(menuPut.getId()), "参数有误, 不能设置自己为上一级");
        menuPut.setWeight(null); // 使用排序接口更新

        AdminMenu adminMenu = getById(menuPut.getParentId());
        Assert.notNull(adminMenu, "菜单不存在");

        if (TreeUtils.ROOT_PARENT_ID != adminMenu.getParentId()) {
            AdminMenu parent = getById(adminMenu.getParentId());
            Assert.notNull(parent, "父级不存在");
            Assert.isTrue(AdminMenu.MENU.equals(parent.getType()), "功能不能包含菜单");
        }

        Assert.isTrue(updateById(menuPut), "更新失败");

        return R.okMsg("更新成功");
    }

    @Transactional
    @Override
    public R<String> create(AdminMenu entity) {
        if (entity.getParentId() != TreeUtils.ROOT_PARENT_ID) {
            AdminMenu parent = getById(entity.getParentId());
            Assert.notNull(parent, "父级不存在");
            Assert.isTrue(AdminMenu.MENU.equals(parent.getType()), "功能不能包含菜单");
        }


        Assert.isTrue(save(entity), "创建失败");


        int index = count(Wrappers.<AdminMenu>lambdaQuery()
                .eq(AdminMenu::getParentId, entity.getParentId())
                .orderByAsc(AdminMenu::getWeight));

        // 重新排序(插入最后)
        sort(entity.getId(), index - 1);

        return R.okMsg("创建成功");
    }
}
