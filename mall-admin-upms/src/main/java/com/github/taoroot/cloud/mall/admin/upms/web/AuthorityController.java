package com.github.taoroot.cloud.mall.admin.upms.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.admin.api.entity.UpmsAuthority;
import com.github.taoroot.cloud.mall.admin.upms.service.AuthorityService;
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
    public R save(@RequestBody UpmsAuthority upmsAuthority) {
        return R.ok(authorityService.saveOrUpdate(upmsAuthority));
    }

    @PutMapping("/authority")
    public R update(@RequestBody UpmsAuthority upmsAuthority) {
        if (upmsAuthority.getParentId().equals(upmsAuthority.getId())) {
            throw new IllegalArgumentException("参数有误, 不能设置自己为上一级");
        }
        return R.ok(authorityService.saveOrUpdate(upmsAuthority));
    }
}
