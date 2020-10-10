package com.github.taoroot.cloud.mall.v1.admin.component;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.github.taoroot.cloud.common.core.vo.LogInfo;
import com.github.taoroot.cloud.common.security.log.LogSaveService;
import com.github.taoroot.cloud.mall.v1.admin.mapper.LogMapper;
import com.github.taoroot.cloud.mall.v1.common.entity.AdminLog;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class LogInfoService implements LogSaveService {

    private final LogMapper logMapper;
    private final DynamicDataSourceProperties dynamicDataSourceProperties;

    @Override
    public void save(LogInfo logInfo) {
        log.info(logInfo);
        AdminLog adminLog = new AdminLog();
        BeanUtils.copyProperties(logInfo, adminLog);
        logMapper.insert(adminLog);
    }
}
