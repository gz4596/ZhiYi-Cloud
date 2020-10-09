package com.github.taoroot.cloud.mall.v1.admin.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.admin.mapper.DeptMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.DeptService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminDept;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, AdminDept> implements DeptService {

    @Override
    public List<Tree<Integer>> tree(Integer parentId) {
        List<AdminDept> list = baseMapper.selectList(Wrappers.<AdminDept>lambdaQuery()
                .eq(AdminDept::getEnabled, true));
        return getTrees(parentId, list);
    }

    @Override
    public R tree(Integer parentId, String name) {
        LambdaQueryWrapper<AdminDept> queryWrapper = Wrappers.lambdaQuery();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like(AdminDept::getName, name);
        }

        List<AdminDept> list = baseMapper.selectList(queryWrapper);
        List<Tree<Integer>> tree = getTrees(parentId, list);
        if (tree.size() != 0) {
            return R.ok(tree);
        }
        return R.ok(list);
    }

    private List<Tree<Integer>> getTrees(Integer parentId, List<AdminDept> list) {
        return TreeUtil.build(list, parentId, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getWeight());
            tree.setName(treeNode.getName());
            tree.putExtra("name", treeNode.getName());
            tree.putExtra("email", treeNode.getEmail());
            tree.putExtra("enabled", treeNode.getEnabled());
            tree.putExtra("leader", treeNode.getLeader());
            tree.putExtra("phone", treeNode.getPhone());
        });
    }
}
