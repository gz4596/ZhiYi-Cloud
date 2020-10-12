package com.github.taoroot.cloud.mall.v1.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminLog;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LogMapper extends BaseMapper<AdminLog> {

    IPage<AdminUser> getPage(@Param("page") Page<AdminLog> page,
                             @Param(Constants.WRAPPER) QueryWrapper<AdminUser> wrapper);
}
