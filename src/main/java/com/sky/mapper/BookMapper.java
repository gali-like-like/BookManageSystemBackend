package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.sky.entity.Book;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select id,bookname,bookauthor,bookprice,bookamount,booktype from t_book limit #{current},10")
    public List<Book> getBooks(Integer current);

    @Select("select count(*) from t_book")
    public Integer getTotal();

}
