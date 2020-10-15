package com.github.taoroot.cloud.mall.v1.admin.web;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.taoroot.cloud.common.core.utils.R;
import com.github.taoroot.cloud.common.security.annotation.Log;
import com.github.taoroot.cloud.mall.v1.admin.mapper.LogMapper;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminLog;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping
public class LogController {

    private final LogMapper logMapper;

    @Log(value = "日志分页")
    @ApiOperation("日志分页")
    @PreAuthorize("hasAuthority('admin:log:page')")
    @GetMapping("/logs")
    public R getPage(Page<AdminLog> page) {
        return R.ok(logMapper.getPage(page, Wrappers.emptyWrapper()));
    }
}
