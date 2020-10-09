package com.github.taoroot.cloud.common.security.log;

import com.github.taoroot.cloud.common.core.vo.LogInfo;

public interface LogSaveService {
    /**
     * 保存系统日志记录
     */
    void save(LogInfo logInfo);
}
