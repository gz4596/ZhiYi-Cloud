package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.annotation.Log;
import com.github.taoroot.cloud.mall.v1.admin.service.UserService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Log("用户创建")
    @ApiOperation("用户创建")
    @PreAuthorize("@hasAuthority('admin:user:create')")
    @PostMapping("/user")
    public R saveItem(@RequestBody AdminUser adminUser) {
        return userService.saveOrUpdateItem(adminUser);
    }

    @Log("用户删除")
    @ApiOperation("用户删除")
    @PreAuthorize("@hasAuthority('admin:user:delete')")
    @DeleteMapping("/user")
    public R delItem(@RequestParam List<Integer> ids) {
        return R.ok(userService.removeByIds(ids));
    }

    @Log("用户更新")
    @ApiOperation("用户更新")
    @PreAuthorize("@hasAuthority('admin:user:update')")
    @PutMapping("/user")
    public R updateItem(@RequestBody AdminUser adminUser) {
        return userService.saveOrUpdateItem(adminUser);
    }

    @Log(value = "用户分页")
    @ApiOperation("用户分页")
    @PreAuthorize("@hasAuthority('admin:user:page')")
    @GetMapping("/users")
    public R getPage(Page<AdminUser> page,
                     @RequestParam(required = false) String username,
                     @RequestParam(required = false) String phone,
                     @RequestParam(required = false) Integer deptId,
                     @RequestParam(required = false) Boolean enabled) {
        return userService.getPage(page, username, phone, deptId, enabled);
    }
}

