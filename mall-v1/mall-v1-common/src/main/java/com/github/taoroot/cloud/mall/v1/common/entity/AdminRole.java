package com.github.taoroot.cloud.mall.v1.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;

@Data
@TableName("admin_role")
@EqualsAndHashCode(callSuper = true)
public class AdminRole extends Model<AdminRole> {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String role;

    private String description;

    private Integer scopeType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableField(typeHandler = JacksonTypeHandler.class, jdbcType = JdbcType.ARRAY)
    private Integer[] scope;

    @TableField(exist = false)
    private Integer[] menus;
}
