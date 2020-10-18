package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.annotation.Log;
import com.github.taoroot.cloud.mall.v1.admin.service.AuthorityService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(tags = "权限管理")
public class AuthorityController {

    private final AuthorityService authorityService;

    @Log(value = "权限分页")
    @ApiOperation("权限分页")
    @PreAuthorize("hasAuthority('admin:authority:page')")
    @GetMapping("/authoritys")
    public R getPage(Page<AdminAuthority> page) {
        return R.ok(authorityService.page(page));
    }
}
