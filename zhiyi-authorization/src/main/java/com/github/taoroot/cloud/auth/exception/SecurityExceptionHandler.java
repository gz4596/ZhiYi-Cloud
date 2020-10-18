package com.github.taoroot.cloud.auth.exception;

import com.github.taoroot.cloud.common.core.utils.R;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author : zhiyi
 * Date: 2020/5/16
 */
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handler(HttpRequestMethodNotSupportedException e) {
        return R.errMsg("不支持 " + e.getMethod() + " 请求方式" );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public R handler(NoHandlerFoundException e) {
        return R.errMsg("不存在 " + e.getRequestURL()  + " 路径");
    }

    @ExceptionHandler(Exception.class)
    public R<String> handler(Exception e) {
        return R.errMsg(e.getMessage());
    }
}
