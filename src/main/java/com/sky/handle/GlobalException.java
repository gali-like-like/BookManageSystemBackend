package com.sky.handle;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sky.entity.Result;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public Result handleException() {
        return Result.fail("操作有误");
    }

}
