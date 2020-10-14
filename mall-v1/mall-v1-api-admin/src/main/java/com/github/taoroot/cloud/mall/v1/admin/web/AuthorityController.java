package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.admin.service.AuthorityService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthorityController {

    private final AuthorityService authorityService;

    @GetMapping("/authorities")
    public R getPage(Page<AdminAuthority> page) {
        return R.ok(authorityService.page(page));
    }
}
