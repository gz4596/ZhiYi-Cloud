package com.github.taoroot.cloud.mall.v1.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.cloud.mall.v1.admin.mapper.LogMapper;
import com.github.taoroot.cloud.mall.v1.admin.service.LogService;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminLog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogServiceImpl extends ServiceImpl<LogMapper, AdminLog> implements LogService {

}
