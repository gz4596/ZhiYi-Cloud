package com.github.taoroot.cloud.mall.v1.admin.mapper;

import com.github.taoroot.cloud.mall.v1.common.entity.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : zhiyi
 * Date: 2020/2/11
 */
@Mapper
public interface RoleMapper extends BaseMapper<AdminRole> {

    IPage<AdminRole> getPage(Page<AdminRole> page);

    List<Integer> selectAuthoritiesByRole(Integer roleId);
}
