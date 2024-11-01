package com.sky.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "图书实列")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
	@Schema(description = "图书id,id唯一标识符")
    private Integer id;
	@Schema(description = "图书名字")
	private String bookName;
	@Schema(description = "图书作者")
    private String bookAuthor;
	@Schema(description = "图书价格")
    private BigDecimal bookPrice;
	@Schema(description = "图书数量")
    private Integer bookAmount;
	@Schema(description = "图书类型")
    private String bookType;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookauthor='" + bookAuthor + '\'' +
                ", bookprice=" + bookPrice +
                ", bookamount=" + bookAmount +
                ", booktype='" + bookType + '\'' +
                '}';
    }

    public Book() {
    }

    public Book(Integer id, String bookName, String bookAuthor, BigDecimal bookPrice, Integer bookAmount, String bookType) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.bookAmount = bookAmount;
        this.bookType = bookType;
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

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(Integer bookAmount) {
        this.bookAmount = bookAmount;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
}
