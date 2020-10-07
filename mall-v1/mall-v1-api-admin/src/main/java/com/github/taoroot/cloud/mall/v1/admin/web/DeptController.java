package com.github.taoroot.cloud.mall.v1.admin.web;

import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.core.utils.TreeUtils;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminDept;
import com.github.taoroot.cloud.mall.v1.admin.service.DeptService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DeptController {

    private final DeptService deptService;

    @PostMapping("/dept")
    public R saveItem(@RequestBody AdminDept adminDept) {
        return R.ok(deptService.save(adminDept));
    }

    @DeleteMapping("/dept")
    public R delItem(@RequestParam List<Integer> ids) {
        return R.ok(deptService.removeByIds(ids));
    }

    @GetMapping("/dept/{id}")
    public R delItem(@PathVariable Integer id) {
        return R.ok(deptService.getById(id));
    }

    @PutMapping("/dept")
    public R updateItem(@RequestBody AdminDept adminDept) {
        if (adminDept.getParentId().equals(adminDept.getId())) {
            throw new IllegalArgumentException("参数有误, 不能设置自己为上一级");
        }
        return R.ok(deptService.updateById(adminDept));
    }

    @GetMapping("/depts")
    public R getPage(@RequestParam(defaultValue = "" + TreeUtils.ROOT_PARENT_ID) Integer parentId,
                     @RequestParam(required = false) String name) {
        return deptService.tree(parentId, name);
    }
}

