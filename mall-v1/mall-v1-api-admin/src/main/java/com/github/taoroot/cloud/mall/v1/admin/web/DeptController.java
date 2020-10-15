package com.github.taoroot.cloud.mall.v1.admin.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.utils.TreeUtils;
import com.github.taoroot.cloud.common.security.annotation.Log;
import com.github.taoroot.cloud.mall.v1.admin.service.DeptService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminDept;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DeptController {

    private final DeptService deptService;

    @Log("部门创建")
    @ApiOperation("部门创建")
    @PreAuthorize("hasAuthority('admin:dept:create')")
    @PostMapping("/dept")
    public R create(@RequestBody AdminDept adminDept) {
        return R.ok(deptService.save(adminDept));
    }

    @Log("部门删除")
    @ApiOperation("部门删除")
    @PreAuthorize("hasAuthority('admin:dept:delete')")
    @DeleteMapping("/dept")
    public R delete(@RequestParam List<Integer> ids) {
        return R.ok(deptService.removeByIds(ids));
    }

    @Log("部门详情")
    @ApiOperation("部门详情")
    @PreAuthorize("hasAuthority('admin:dept:get')")
    @GetMapping("/dept/{id}")
    public R get(@PathVariable Integer id) {
        return R.ok(deptService.getById(id));
    }

    @Log("部门更新")
    @ApiOperation("部门更新")
    @PreAuthorize("hasAuthority('admin:dept:update')")
    @PutMapping("/dept")
    public R updateItem(@RequestBody AdminDept adminDept) {
        if (adminDept.getParentId().equals(adminDept.getId())) {
            throw new IllegalArgumentException("参数有误, 不能设置自己为上一级");
        }
        return R.ok(deptService.updateById(adminDept));
    }

    @Log(value = "部门所有")
    @ApiOperation("部门属性")
    @PreAuthorize("hasAuthority('admin:dept:page')")
    @GetMapping("/depts")
    public R getPage(@RequestParam(defaultValue = "" + TreeUtils.ROOT_PARENT_ID) Integer parentId,
                     @RequestParam(required = false) String name) {
        return deptService.tree(parentId, name);
    }
}

