package com.dreamer.demoproject.exception;

import com.dreamer.demoproject.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice//全局地捕获应用程序中抛出的异常，并对这些异常进行统一的处理
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//用于定义在遇到特定类型的异常时执行的方法,参数是异常类型
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败");
    }
}
