package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.admin.service.PostService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminPost;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public R saveItem(@RequestBody AdminPost adminPost) {
        return R.ok(postService.save(adminPost));
    }

    @DeleteMapping("/post")
    public R delItem(@RequestParam List<Integer> ids) {
        return R.ok(postService.removeByIds(ids));
    }

    @PutMapping("/post")
    public R updateItem(@RequestBody AdminPost adminPost) {
        return R.ok(postService.save(adminPost));
    }

    @GetMapping("/posts")
    public R getPage(Page<AdminPost> page) {
        return R.ok(postService.page(page));
    }
}
