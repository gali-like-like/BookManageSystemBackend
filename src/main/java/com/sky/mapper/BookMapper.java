package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sky.entity.Book;

@Mapper
public interface BookMapper {

    @Select("select id,bookname,bookauthor,bookprice,bookamount,booktype from t_book limit #{current},10")
    public List<Book> getBooks(Integer current);

    @Select("select count(*) from t_book")
    public Integer getTotal();
    
    @Delete("delete from t_book where id = #{id}")
    public void removeBookById(Integer id);
    
    public Book updateBook(Book book);
}
