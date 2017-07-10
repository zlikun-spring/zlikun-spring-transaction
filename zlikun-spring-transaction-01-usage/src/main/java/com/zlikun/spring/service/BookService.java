package com.zlikun.spring.service;

import com.zlikun.spring.dao.BookDao;
import com.zlikun.spring.dto.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/7/10 10:01
 */
@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(getClass()) ;

    @Autowired
    BookDao bookDao ;

    /**
     * 主键查询，测试查询事务
     * @param bookId
     * @return
     */
    // 不支持事务，如果存在事务，则丢弃之
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Book get(long bookId) {
        logger.info("执行查询请求，参数：{}" ,bookId);
        return bookDao.get(bookId) ;
    }

    /**
     * 更新操作，测试更新缓存
     * @param book
     */
    // 下面使用的隔离级别、传播方式都是默认值
    @Transactional(isolation = Isolation.REPEATABLE_READ ,propagation = Propagation.REQUIRED)
    public void update(Book book) {
        Assert.notNull(book ,"参数book不能为空");
        Assert.notNull(book.getId() ,"参数book中id字段不能为空");
        logger.info("执行更新请求，参数：id = {} ,price = {}" ,book.getId() ,book.getPrice());
        bookDao.update(book);
    }

}
