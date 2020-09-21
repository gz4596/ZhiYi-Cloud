package com.github.taoroot.cloud.mall.admin.upms.mapper;


import com.github.taoroot.cloud.common.core.datascope.DataScope;
import com.github.taoroot.cloud.mall.admin.api.entity.UpmsAuthority;
import com.github.taoroot.cloud.mall.admin.api.entity.UpmsRole;
import com.github.taoroot.cloud.mall.admin.api.entity.UpmsUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : zhiyi
 * Date: 2020/2/11
 */
public interface UserMapper extends BaseMapper<UpmsUser> {

    List<Integer> roleIds(@Param("userId") String userId);

    List<UpmsRole> roles(@Param("userId") String userId);

    List<String> roleNames(@Param("userId") String userId);

    List<UpmsAuthority> authorities(@Param("userId") String userId, @Param("type") Integer type);

    List<String> authorityNames(@Param("userId") Integer userId, @Param("type") Integer type);

    IPage<UpmsUser> getPage(@Param("page") Page<UpmsUser> page, @Param("dataScope") DataScope dataScope,
                            @Param("username") String username,
                            @Param("phone") String phone,
                            @Param("deptId") Integer deptId,
                            @Param("enabled") Boolean enabled);
}
