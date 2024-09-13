package com.sky;

import com.sky.entity.Book;
import com.sky.mapper.BookMapper;
import com.sky.server.BookServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookManageSystemApplicationTests {

	@Autowired
	private BookServer server;



	@Test
	void contextLoads() {
	}

	@Test
	void testMapper() {
//		List<Book> books = server.getBooks(0);
//		System.out.println(books);
	}

}
