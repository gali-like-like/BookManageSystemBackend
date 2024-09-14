package com.sky.entity;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "图书实列")
public class Book {
	@Schema(description = "图书id,id唯一标识符")
    private Integer id;
	@Schema(description = "图书名字")
	private String bookName;
	@Schema(description = "图书作者")
    private String bookauthor;
	@Schema(description = "图书价格")
    private BigDecimal bookprice;
	@Schema(description = "图书数量")
    private Integer bookamount;
	@Schema(description = "图书类型")
    private String booktype;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookauthor='" + bookauthor + '\'' +
                ", bookprice=" + bookprice +
                ", bookamount=" + bookamount +
                ", booktype='" + booktype + '\'' +
                '}';
    }

    public Book() {
    }

    public Book(Integer id, String bookName, String bookauthor, BigDecimal bookprice, Integer bookamount, String booktype) {
        this.id = id;
        this.bookName = bookName;
        this.bookauthor = bookauthor;
        this.bookprice = bookprice;
        this.bookamount = bookamount;
        this.booktype = booktype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookauthor() {
        return bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        this.bookauthor = bookauthor;
    }

    public BigDecimal getBookprice() {
        return bookprice;
    }

    public void setBookprice(BigDecimal bookprice) {
        this.bookprice = bookprice;
    }

    public Integer getBookamount() {
        return bookamount;
    }

    public void setBookamount(Integer bookamount) {
        this.bookamount = bookamount;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }
}
