package com.sky.server;

import com.sky.entity.Book;
import com.sky.mapper.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.List;

@Service
public class BookServer {
    private Logger logger = LoggerFactory.getLogger(BookServer.class);
    @Autowired
    private BookMapper mapper;
    //ApplicationContext.getBean()

    @Transactional(rollbackFor = Exception.class)
    public HashMap<String,Object> getBooks(Integer cur) {
        List<Book> books = mapper.getBooks((cur-1)*10);
        Integer total = mapper.getTotal();//总记录数
        HashMap<String,Object> returnData = new HashMap<>();
        returnData.put("total",total);
        returnData.put("currentPage",books);//当前页
        returnData.put("totalPage",total%10==0?total/10:total/10+1);//总页数,每页10条
        return returnData;
    }
    //查询总记录数
    public Integer getTotal() {
        Integer total = mapper.getTotal();
        logger.info("total:{}",total);
        return mapper.getTotal();
    }
}
