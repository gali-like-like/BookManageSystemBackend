package com.sky.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.sky.entity.Book;
import com.sky.entity.Result;
import com.sky.server.BookServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class BookController {
    //后端tomcat,端口8080 前端nginx,端口80 访问nginx项目,当然是通过http
    private Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookServer bookServer;
    @CrossOrigin(origins = "http://127.0.0.1")//跨越配置
    @GetMapping("/currentPage")
    public Result currentPage(Integer current) throws ExecutionException, InterruptedException {
        CompletableFuture<HashMap<String,Object>> future = CompletableFuture.supplyAsync(() -> {
           return bookServer.getBooks(current);
        });
        CompletableFuture<HashMap<String,Object>> fianllyFuture = future.handle((result,e) -> {
            //异常判断
            if(e == null) {
                return result;
            }
            return null;
        });
        HashMap<String,Object> books = fianllyFuture.get();
        logger.info("BOOKS:\n"+books.toString());
        return Result.success("查询成功",books);
    }

    @GetMapping("/getTotal")
    public Result getTotal() {
        Integer total = bookServer.getTotal();
        return Result.success("查询成功",total);
    }

}
