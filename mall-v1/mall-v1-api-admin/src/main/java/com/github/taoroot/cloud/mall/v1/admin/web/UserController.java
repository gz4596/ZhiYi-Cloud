package com.github.taoroot.cloud.mall.v1.admin.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminUser;
import com.github.taoroot.cloud.mall.v1.admin.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public R saveItem(@RequestBody AdminUser adminUser) {
        return userService.saveOrUpdateItem(adminUser);
    }

    @DeleteMapping("/user")
    public R delItem(@RequestParam List<Integer> ids) {
        return R.ok(userService.removeByIds(ids));
    }

    @PutMapping("/user")
    public R updateItem(@RequestBody AdminUser adminUser) {
        return userService.saveOrUpdateItem(adminUser);
    }

    @GetMapping("/users")
    public R getPage(Page<AdminUser> page,
                     @RequestParam(required = false) String username,
                     @RequestParam(required = false) String phone,
                     @RequestParam(required = false) Integer deptId,
                     @RequestParam(required = false) Boolean enabled) {
        return userService.getPage(page, username, phone, deptId, enabled);
    }
}

