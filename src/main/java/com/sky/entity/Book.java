package com.sky.entity;

import java.math.BigDecimal;

public class Book {
    private Integer id;
    private String bookName;
    private String bookauthor;
    private BigDecimal bookprice;
    private Integer bookamount;
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
