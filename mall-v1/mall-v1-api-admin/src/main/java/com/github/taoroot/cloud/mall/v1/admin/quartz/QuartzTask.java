package com.github.taoroot.cloud.mall.v1.admin.quartz;

import org.springframework.stereotype.Component;

/**
 * 定时任务测试
 */
@Component
public class QuartzTask {

    public void hasParams(String params) {
        System.out.println("[定时任务测试]有参方法：" + params);
    }

    public void NoParams() {
        System.out.println("[定时任务测试]无参方法");
    }
}
