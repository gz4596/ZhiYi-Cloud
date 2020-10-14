package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.admin.service.RoleService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminRole;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/role")
    public R saveItem(@RequestBody AdminRole adminRole) {
        return R.ok(roleService.saveOrUpdateItem(adminRole));
    }

    @DeleteMapping("/role")
    public R delItem(@RequestParam List<Integer> ids) {
        return R.ok(roleService.removeByIds(ids));
    }

    @PutMapping("/role")
    public R updateItem(@RequestBody AdminRole adminRole) {
        return roleService.saveOrUpdateItem(adminRole);
    }

    @GetMapping("/roles")
    public R getPage(Page<AdminRole> page) {
        return roleService.getPage(page);
    }
}
