package com.sky.controller;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sky.entity.Book;
import com.sky.entity.Result;
import com.sky.server.BookServer;
import com.sky.vo.PageVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.*;
@RestController
@CrossOrigin(origins = "*")//跨越配置
@Tag(name = "图书",description = "这是图书管理的接口")//用于生成接口文档所需要的注解
public class BookController {
    //后端tomcat,端口8080 前端nginx,端口80 访问nginx项目,当然是通过http
    private Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookServer bookServer;

    @GetMapping("/currentPage")
    @Operation(summary = "分页查询图书",description = "根据当前页查询出当前页的数据,总页数,总记录数信息")
    @Cacheable(cacheNames = "pageId",key="#current",condition = "#current != null and #current >=1")
    //满足当前页不能为空并且当前页>=1,才能添加缓存,如果缓存当中有了这条数据,就会直接返回结果
    public Result currentPage(@Parameter(name = "current",description = "当前页") @Valid @NotNull(message = "当前页不能为空!") @Min(value=1,message = "页码必须大于等于1") Integer current) throws ExecutionException, InterruptedException, TimeoutException {
        PageVo<Book> result =  bookServer.getBooks(current);
        return Result.success("查询成功",result);
    }

    @GetMapping("/getTotal")
    @Operation(summary = "查询总页数")
    @Cacheable(cacheNames = "total",key="1")
    public Result getTotal() {
        Integer total = bookServer.getTotal();
        return Result.success("查询成功",total);
    }

    @PostMapping("/book")
    @Operation(summary = "添加图书")
    @CachePut(cacheNames = "cacheBook",key="#book.getId()",condition = "#book != null")//book不能为空才能添加到缓存
    public Result insert(@RequestBody Book book) throws ExecutionException, InterruptedException, TimeoutException {
        bookServer.addBook(book);
        return Result.success("添加图书成功",book.getId());
    }

    @PutMapping("/book")
    @Operation(summary = "修改图书",description = "根据图书的上传信息,对该图书进行修改,并返回修改后的图书")
    @CachePut(cacheNames = "cacheBook",key = "#book.getId()",condition = "#book != null")
    public Result updateBook(@Parameter(name = "图书实例",description = "包含图书的id,以及一些其他图书的修改信息") @RequestBody Book book) throws InterruptedException, ExecutionException, TimeoutException {
        logger.info("book:"+book.toString());
        Book afterBook =  bookServer.updateBook(book);
    	return Result.success("更新成功", afterBook);
    }

    @DeleteMapping("/book/{id}")
    @Operation(summary = "删除图书",description = "根据图书id删除对应id的图书")
    @CacheEvict(cacheNames = "cacheBook",key="#id",condition = "#id != null and #id >= 1")//只有id不为空且id>=1时才会判断缓存中是否有对应的key,有则删除
    public Result removeBook(@PathVariable @Validated @Min(message = "必须大于等于1",value=1) Integer id) throws InterruptedException, ExecutionException, TimeoutException {
    	bookServer.removeBookById(id);
    	return Result.success("删除成功", null);
    }
}
