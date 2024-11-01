package com.sky.server;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sky.entity.Book;
import com.sky.mapper.BookMapper;
import com.sky.vo.PageVo;

@Service
public class BookServer {
    private Logger logger = LoggerFactory.getLogger(BookServer.class);
    @Autowired
    private BookMapper mapper;
    //ApplicationContext.getBean()

    public PageVo getBooks(Integer cur) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<List<Book>> futureGetBooks = CompletableFuture.supplyAsync(() -> {
            return mapper.getBooks((cur-1)*10);
        });
        CompletableFuture<Integer> futureGetTotal = CompletableFuture.supplyAsync(() -> {
            return mapper.getTotal();
        });
        CompletableFuture<Void> result =  CompletableFuture.allOf(futureGetBooks,futureGetTotal);
        result.get(3,TimeUnit.SECONDS);
        Integer total = futureGetTotal.get();
        Integer totalPage = total%10==0?total/10:total/10+1;
        PageVo<Book> pageVo = PageVo.getPageVo(total, totalPage, futureGetBooks.get());
        return pageVo;
    }
    //查询总记录数
    public Integer getTotal() {
        Integer total = mapper.getTotal();
        logger.info("total:{}",total);
        return mapper.getTotal();
    }

    public void removeBookById(Integer id) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            mapper.removeBookById(id);
        });
        future.get(3,TimeUnit.SECONDS);
        mapper.removeBookById(id);
    }

    public Book updateBook(Book book) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> mapper.updateBook(book));
        future.get(3,TimeUnit.SECONDS);
        return book;
    }

    public void addBook(Book book) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            mapper.addBook(book);
        });
        future.get(3, TimeUnit.SECONDS);
    }
}
