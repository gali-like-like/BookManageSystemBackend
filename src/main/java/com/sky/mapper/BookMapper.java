package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.sky.entity.Book;

@Mapper
public interface BookMapper {

    @Select("select id,book_name,book_author,book_price,book_amount,book_type from t_book limit #{current},10")
    public List<Book> getBooks(Integer current);

    @Select("select count(*) from t_book")
    public Integer getTotal();

    @Delete("delete from t_book where id = #{id}")
    public void removeBookById(Integer id);

    public void updateBook(Book book);

    @Insert("insert into t_book values (null,#{bookName},#{bookAuthor},#{bookPrice},#{bookAmount},#{bookType})")
    public Integer addBook(Book book);
}
