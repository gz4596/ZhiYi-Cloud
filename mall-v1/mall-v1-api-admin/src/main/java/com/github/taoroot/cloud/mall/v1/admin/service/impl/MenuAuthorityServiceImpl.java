package com.github.taoroot.cloud.mall.v1.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.admin.mapper.MenuAuthorityMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.MenuAuthorityService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminMenuAuthority;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class MenuAuthorityServiceImpl extends ServiceImpl<MenuAuthorityMapper, AdminMenuAuthority> implements MenuAuthorityService {


    @Override
    @Transactional
    public R<String> addAuthorityByMenu(Integer menuId, List<Integer> ids) {
        List<AdminMenuAuthority> collect = ids.stream().distinct()
                .map(authorityId -> new AdminMenuAuthority(null, menuId, authorityId))
                .collect(Collectors.toList());

        Assert.isTrue(saveBatch(collect), "保存失败");

        return R.okMsg("添加成功");
    }

    @Override
    @Transactional
    public R<String> removeAuthorityByMenu(Integer menuId, List<Integer> ids) {
        Assert.isTrue(removeByIds(ids), "移除失败");
        return R.okMsg("移除成功");
    }
}
