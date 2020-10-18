package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.annotation.Log;
import com.github.taoroot.cloud.mall.v1.admin.service.PostService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminPost;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Api(tags = "岗位管理")
public class PostController {

    private final PostService postService;

    @Log("岗位创建")
    @ApiOperation("岗位创建")
    @PreAuthorize("hasAuthority('admin:post:create')")
    @PostMapping("/post")
    public R create(@RequestBody AdminPost adminPost) {
        return R.ok(postService.save(adminPost));
    }

    @Log("岗位删除")
    @ApiOperation("岗位删除")
    @PreAuthorize("hasAuthority('admin:post:delete')")
    @DeleteMapping("/post")
    public R delete(@RequestParam List<Integer> ids) {
        return R.ok(postService.removeByIds(ids));
    }

    @Log("岗位更新")
    @ApiOperation("岗位更新")
    @PreAuthorize("hasAuthority('admin:post:update')")
    @PutMapping("/post")
    public R updateItem(@RequestBody AdminPost adminPost) {
        return R.ok(postService.save(adminPost));
    }

    @Log(value = "岗位分页")
    @ApiOperation("岗位分页")
    @PreAuthorize("hasAuthority('admin:post:page')")
    @GetMapping("/posts")
    public R getPage(Page<AdminPost> page) {
        return R.ok(postService.page(page));
    }
}
