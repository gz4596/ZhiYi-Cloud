package com.github.taoroot.cloud.mall.v1.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminMenuAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthorityMapper extends BaseMapper<AdminAuthority> {

    IPage<AdminAuthority> selectByMenu(@Param("page") Page<AdminAuthority> page,
                                       @Param(Constants.WRAPPER) LambdaQueryWrapper<AdminMenuAuthority> wrapper);

}
