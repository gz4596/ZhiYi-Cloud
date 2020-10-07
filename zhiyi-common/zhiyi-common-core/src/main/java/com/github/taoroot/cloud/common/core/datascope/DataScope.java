package com.github.taoroot.cloud.common.core.datascope;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class DataScope implements Serializable {
    /**
     * sql
     */
    @Setter
    @Getter
    static String sql = "SELECT ur.scope_type, ur.scope FROM admin_role ur where ur.id IN (?) order by ur.scope_type limit 1";
    /**
     * 限制范围为部门时字段名称
     */
    private String scopeName = "dept_id";
    /**
     * 当权限范围本用户时字段名称
     */
    private String scopeOwnName = "user_id";

    /**
     * 当前用户ID
     */
    private Integer userId;

    /**
     * 具体的数据范围
     */
    private List<Integer> deptIds = new ArrayList<>();

    /**
     * 基于角色
     */
    private List<Integer> roleIds = new ArrayList<>();

    /**
     * 默认查询所有
     */
    private DataScopeTypeEnum dataScopeTypeEnum = DataScopeTypeEnum.ALL;
}
