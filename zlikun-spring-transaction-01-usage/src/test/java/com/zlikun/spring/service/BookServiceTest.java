package com.zlikun.spring.service;

import com.zlikun.spring.dto.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/7/10 10:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    BookService bookService ;

    @Test
    public void get() {
        Book book = bookService.get(1L) ;
        Assert.assertNotNull(book);
        Assert.assertEquals("9787121313011" ,book.getIsbn());
    }

    @Test @Rollback
    public void update() {
        Book book = new Book() ;
        book.setId(1L);
        book.setPrice(60.0F);
        bookService.update(book);
    }

}
