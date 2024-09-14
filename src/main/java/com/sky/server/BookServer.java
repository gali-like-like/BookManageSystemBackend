package com.sky.server;

import java.util.List;

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

    @Transactional(rollbackFor = Exception.class)
    public PageVo getBooks(Integer cur) {
        List<Book> books = mapper.getBooks((cur-1)*10);
        Integer total = mapper.getTotal();//总记录数
        PageVo<Book> pageVo = PageVo.getPageVo(total, total, books);
        return pageVo;
    }
    //查询总记录数
    public Integer getTotal() {
        Integer total = mapper.getTotal();
        logger.info("total:{}",total);
        return mapper.getTotal();
    }
    
    public void removeBookById(Integer id) {
    	mapper.removeBookById(id);
    }
    
    public Book updateBook(Book book) {
    	return mapper.updateBook(book);
    }
}
