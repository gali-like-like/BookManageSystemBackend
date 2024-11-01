package com.sky.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sky.entity.Result;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestControllerAdvice
@Slf4j
public class GlobalException {

    @Order(10)
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(),e);
        return Result.fail("操作有误");
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public Result handleException(HandlerMethodValidationException e) {
        log.error(e.getMessage(),e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValid(MethodArgumentNotValidException e) {
        log.info(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return Result.fail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(TimeoutException.class)
    public Result handleTimeoutException(TimeoutException e) {
        log.error(e.getLocalizedMessage(),e);
        return Result.fail("处理超时,请重试");
    }

    @ExceptionHandler({InterruptedException.class, ExecutionException.class})
    public Result handleInterruptedException(InterruptedException e) {
        log.error(e.getMessage(), e);
        return Result.fail("系统错误");
    }

    @ExceptionHandler(DataAccessException.class)
    public Result handleDataAccessException(DataAccessException e) {
        log.error(e.getMessage());
        return Result.fail("数据库异常");
    }

}
