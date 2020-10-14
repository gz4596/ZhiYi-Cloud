package com.github.taoroot.cloud.mall.v1.admin.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.admin.service.AuthorityService;
import com.github.taoroot.cloud.mall.v1.admin.service.MenuService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminMenu;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("菜单")
@RestController
@AllArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final AuthorityService authorityService;

    @ApiOperation("菜单列表")
    @GetMapping(value = "/menus")
    public R<List<?>> getTree(@RequestParam(defaultValue = "") String title,
                              @RequestParam(required = false) Boolean hidden) {
        return menuService.getTree(title, hidden);
    }


    @ApiOperation("菜单详情")
    @GetMapping("/menu/{id}")
    public R<AdminMenu> getById(@PathVariable Integer id) {
        return R.ok(menuService.getById(id));
    }


    @ApiOperation("菜单删除")
    @DeleteMapping("/menu/{id}")
    public R<String> remove(@PathVariable Integer id) {
        return menuService.remove(id);
    }


    @ApiOperation("菜单创建")
    @PostMapping("/menu")
    public R<String> save(@RequestBody AdminMenu adminMenu) {
        return menuService.create(adminMenu);
    }


    @ApiOperation("菜单更新")
    @PutMapping("/menu")
    public R<String> update(@RequestBody AdminMenu adminMenu) {
        return menuService.update(adminMenu);
    }


    @ApiOperation("菜单排序")
    @PutMapping("/menu/sort")
    public R<String> sort(Integer menuId, Integer index) {
        return menuService.sort(menuId, index);
    }


    @ApiOperation("菜单权限列表")
    @GetMapping("/menu/authorities")
    public R<List<AdminAuthority>> getPage(String menuId) {
        return authorityService.pageByMenu(menuId);
    }
}
