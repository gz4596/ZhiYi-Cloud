package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.annotation.Log;
import com.github.taoroot.cloud.mall.v1.admin.service.AuthorityService;
import com.github.taoroot.cloud.mall.v1.admin.service.MenuAuthorityService;
import com.github.taoroot.cloud.mall.v1.admin.service.MenuService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminAuthority;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminMenu;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("菜单")
@RestController
@AllArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final AuthorityService authorityService;
    private final MenuAuthorityService menuAuthorityService;

    @Log(value = "菜单树")
    @ApiOperation("菜单树")
    @PreAuthorize("hasAuthority('admin:menu:tree')")
    @GetMapping(value = "/menus")
    public R<List<?>> tree(@RequestParam(defaultValue = "") String title,
                           @RequestParam(required = false) Boolean hidden) {
        return menuService.getTree(title, hidden);
    }


    @Log("菜单详情")
    @ApiOperation("菜单详情")
    @PreAuthorize("hasAuthority('admin:menu:get')")
    @GetMapping("/menu/{id}")
    public R<AdminMenu> getById(@PathVariable Integer id) {
        return R.ok(menuService.getById(id));
    }


    @Log("菜单删除")
    @ApiOperation("菜单删除")
    @PreAuthorize("hasAuthority('admin:menu:delete')")
    @DeleteMapping("/menu/{id}")
    public R<String> delete(@PathVariable Integer id) {
        return menuService.remove(id);
    }


    @Log("菜单创建")
    @ApiOperation("菜单创建")
    @PreAuthorize("hasAuthority('admin:menu:create')")
    @PostMapping("/menu")
    public R<String> create(@RequestBody AdminMenu adminMenu) {
        return menuService.create(adminMenu);
    }


    @Log("菜单更新")
    @ApiOperation("菜单更新")
    @PreAuthorize("hasAuthority('admin:menu:update')")
    @PutMapping("/menu")
    public R<String> update(@RequestBody AdminMenu adminMenu) {
        return menuService.update(adminMenu);
    }


    @Log("菜单排序")
    @ApiOperation("菜单排序")
    @PreAuthorize("hasAuthority('admin:menu:sort')")
    @PutMapping("/menu/sort")
    public R<String> sort(Integer menuId, Integer index) {
        return menuService.sort(menuId, index);
    }


    @Log("菜单的权限")
    @ApiOperation("菜单的权限")
    @PreAuthorize("hasAuthority('admin:menu:authoritys')")
    @GetMapping("/menu/authoritys")
    public R<IPage<AdminAuthority>> getPage(Integer menuId, Page<AdminAuthority> page) {
        return authorityService.pageByMenu(menuId, page);
    }

    @Log("菜单的权限新增")
    @ApiOperation("菜单的权限新增")
    @PreAuthorize("hasAuthority('admin:menu:authority:add')")
    @PostMapping("/menu/authority")
    public R<String> getPage(@RequestParam("menuId") Integer menuId, @RequestBody List<Integer> ids) {
        return menuAuthorityService.addAuthorityByMenu(menuId, ids);
    }

    @Log("菜单的权限移除")
    @ApiOperation("菜单的权限移除")
    @PreAuthorize("hasAuthority('admin:menu:authority:delete')")
    @DeleteMapping("/menu/authority")
    public R<String> deleteMenuAuthority(@RequestParam("menuId") Integer menuId, @RequestBody List<Integer> ids) {
        return menuAuthorityService.removeAuthorityByMenu(menuId, ids);
    }
}
