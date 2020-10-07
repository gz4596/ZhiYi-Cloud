package com.github.taoroot.cloud.mall.v1.admin.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminDept;

import java.util.List;


public interface DeptService extends IService<AdminDept> {

    List<Tree<Integer>> tree(Integer parentId);

    R tree(Integer parentId, String name);
}
