package com.sky.controller;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.entity.Book;
import com.sky.entity.Result;
import com.sky.server.BookServer;
import com.sky.vo.PageVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

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
    @ApiResponse(description = "")
    public Result currentPage(@Parameter(name = "当前页",required = true) Integer current) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<PageVo<Book>> future = CompletableFuture.supplyAsync(() -> {
           return bookServer.getBooks(current);
        });
        CompletableFuture<PageVo<Book>> fianllyFuture = future.handle((result,e) -> {
            //异常判断
            if(e == null) {
                return result;
            }
            return null;
        });
        PageVo<Book> books = fianllyFuture.get(3,TimeUnit.SECONDS);
        logger.info("BOOKS:\n"+books.toString());
        return Result.success("查询成功",books);
    }
    
    
    @GetMapping("/getTotal")
    @Operation(summary = "查询总页数")
    public Result getTotal() {
        Integer total = bookServer.getTotal();
        return Result.success("查询成功",total);
    }
    
    @PutMapping("/book")
    @Operation(summary = "修改图书",description = "根据图书的上传信息,对该图书进行修改,并返回修改后的图书")
    public Result updateBook(@Parameter(name = "图书实例",description = "包含图书的id,以及一些其他图书的修改信息") Book book) throws InterruptedException, ExecutionException, TimeoutException {
    	CompletableFuture<Book> future = CompletableFuture.supplyAsync(()-> {
    		return  bookServer.updateBook(book);
    	}).handle((result,e) -> {
    		if(Objects.isNull(e)) {
    			return result;
    		}
    		return null;
    	});
    	Book result = future.get(3,TimeUnit.SECONDS);
    	return Result.success("更新成功", result);
    }
    
    @DeleteMapping("/book/{id}")
    @Operation(summary = "删除图书",description = "根据图书id删除对应id的图书")
    public Result removeBook(@PathVariable Integer id) throws InterruptedException, ExecutionException, TimeoutException {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
    		bookServer.removeBookById(id);
    	}).handle((result,e)-> {
    		if(Objects.isNull(e)) {
    			return result;
    		}
    		return null;
    	});
    	future.get(3,TimeUnit.SECONDS);
    	return Result.success("删除成功", null);
    }
}
