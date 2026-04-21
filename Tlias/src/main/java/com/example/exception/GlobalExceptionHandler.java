package com.example.exception;


//全局异常处理器

import com.example.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //日志
    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public Result handleException(Exception e) {
        logger.error("发生异常: ", e);
        return Result.error("发生异常: " + e.getMessage());
    }

    //处理自定义DuplicateKeyException异常
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error("数据库中已存在该记录: ", e);
        return Result.error("数据库中已存在该记录: " + e.getMessage());
    }
}
