package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.annotation.Log;
import com.github.taoroot.cloud.mall.v1.admin.service.RoleService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Api(tags = "角色管理")
public class RoleController {

    private final RoleService roleService;

    @Log("角色创建")
    @ApiOperation("角色创建")
    @PreAuthorize("hasAuthority('admin:role:create')")
    @PostMapping("/role")
    public R create(@RequestBody AdminRole adminRole) {
        return R.ok(roleService.saveOrUpdateItem(adminRole));
    }


    @Log("角色删除")
    @ApiOperation("角色删除")
    @PreAuthorize("hasAuthority('admin:role:delete')")
    @DeleteMapping("/role")
    public R delete(@RequestParam List<Integer> ids) {
        return R.ok(roleService.removeByIds(ids));
    }


    @Log("角色更新")
    @ApiOperation("角色更新")
    @PreAuthorize("hasAuthority('admin:role:update')")
    @PutMapping("/role")
    public R update(@RequestBody AdminRole adminRole) {
        return roleService.saveOrUpdateItem(adminRole);
    }


    @Log(value = "角色分页")
    @ApiOperation("角色分页")
    @PreAuthorize("hasAuthority('admin:role:page')")
    @GetMapping("/roles")
    public R page(Page<AdminRole> page) {
        return roleService.getPage(page);
    }
}
