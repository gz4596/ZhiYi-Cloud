package com.github.taoroot.cloud.mall.v1.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.datascope.EnableScope;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminRole;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : zhiyi
 * Date: 2020/2/11
 */
@Mapper
public interface UserMapper extends BaseMapper<AdminUser> {

    List<Integer> roleIds(@Param("userId") Integer userId);

    List<AdminRole> roles(@Param("userId") Integer userId);

    List<String> roleNames(@Param("userId") Integer userId);

    List<AdminAuthority> authorities(@Param("userId") Integer userId, @Param("type") Integer type);

    List<String> authorityNames(@Param("userId") Integer userId, @Param("type") Integer type);

    @EnableScope(scopeOwnName = "id")
    IPage<AdminUser> getPage(@Param("page") Page<AdminUser> page,
                             @Param("username") String username,
                             @Param("phone") String phone,
                             @Param("deptId") Integer deptId,
                             @Param("enabled") Boolean enabled);
}
