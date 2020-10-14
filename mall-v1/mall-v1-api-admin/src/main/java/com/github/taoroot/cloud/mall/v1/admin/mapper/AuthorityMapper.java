package com.github.taoroot.cloud.mall.v1.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminMenuAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthorityMapper extends BaseMapper<AdminAuthority> {

    List<AdminAuthority> selectByMenu(@Param(Constants.WRAPPER) LambdaQueryWrapper<AdminMenuAuthority> wrapper);
}
