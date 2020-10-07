package com.github.taoroot.cloud.common.mybatis;


import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MybatisAutoConfiguration {

    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        List<ISqlParser> sqlParserList = new ArrayList<>();
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }
}
