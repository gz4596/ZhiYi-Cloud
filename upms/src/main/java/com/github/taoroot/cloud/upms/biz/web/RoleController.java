package com.github.taoroot.cloud.upms.biz.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.upms.api.entity.UpmsRole;
import com.github.taoroot.cloud.upms.biz.service.RoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/role")
    public R saveItem(@RequestBody UpmsRole upmsRole) {
        return R.ok(roleService.saveOrUpdateItem(upmsRole));
    }

    @DeleteMapping("/role")
    public R delItem(@RequestParam List<Integer> ids) {
        return R.ok(roleService.removeByIds(ids));
    }

    @PutMapping("/role")
    public R updateItem(@RequestBody UpmsRole upmsRole) {
        return roleService.saveOrUpdateItem(upmsRole);
    }

    @GetMapping("/roles")
    public R getPage(Page<UpmsRole> page) {
        return roleService.getPage(page);
    }
}
