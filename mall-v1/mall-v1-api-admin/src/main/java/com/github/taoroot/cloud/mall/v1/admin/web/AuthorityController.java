package com.github.taoroot.cloud.mall.v1.admin.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;
import com.github.taoroot.cloud.mall.v1.admin.service.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthorityController {
    private final AuthorityService authorityService;

    @GetMapping(value = "/authorities")
    public R getTree(@RequestParam(defaultValue = "") String title, @RequestParam(required = false) Boolean hidden) {
        return authorityService.getTree(title, hidden);
    }

    @GetMapping("/authority/{id}")
    public R getById(@PathVariable Integer id) {
        return R.ok(authorityService.getById(id));
    }

    @DeleteMapping("/authority/{id}")
    public R removeById(@PathVariable Integer id) {
        return R.ok(authorityService.removeById(id));
    }

    @PostMapping("/authority")
    public R save(@RequestBody AdminAuthority adminAuthority) {
        return R.ok(authorityService.saveOrUpdate(adminAuthority));
    }

    @PutMapping("/authority")
    public R update(@RequestBody AdminAuthority adminAuthority) {
        if (adminAuthority.getParentId().equals(adminAuthority.getId())) {
            throw new IllegalArgumentException("参数有误, 不能设置自己为上一级");
        }
        return R.ok(authorityService.saveOrUpdate(adminAuthority));
    }
}
