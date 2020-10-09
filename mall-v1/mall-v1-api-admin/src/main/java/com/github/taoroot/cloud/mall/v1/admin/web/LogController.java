package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.mall.v1.admin.service.LogService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminLog;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping
public class LogController {

    private final LogService logService;

    @GetMapping("/logs")
    public R getPage(Page<AdminLog> page) {
        return R.ok(logService.page(page));
    }
}
