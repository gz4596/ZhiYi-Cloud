package com.github.taoroot.cloud.mall.v1.admin.web;

import cn.hutool.core.annotation.AnnotationUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorityControllerTest {

    @Test
    public void select() {
        Test annotation = AnnotationUtil.getAnnotation(AuthorityControllerTest.class, Test.class);
        System.out.println(annotation);
    }
}
