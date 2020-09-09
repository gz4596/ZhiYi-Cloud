package com.github.taoroot.cloud.upms.biz.service;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.upms.api.entity.UpmsDept;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface DeptService extends IService<UpmsDept> {

    List<Tree<Integer>> tree(Integer parentId);

    R tree(Integer parentId, String name);
}
