package com.zlikun.spring.dao;

import com.zlikun.spring.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/7/10 10:06
 */
@Repository
public class BookDao {

    @Autowired
    JdbcTemplate jdbcTemplate ;

    public Book get(long bookId) {
        return jdbcTemplate.queryForObject("SELECT * FROM TBL_BOOK WHERE ID = ?"
                ,new BeanPropertyRowMapper<Book>(Book.class) ,bookId) ;
    }

    public void update(final Book book) {
        Assert.notNull(book ,"参数book不能为空");
        Assert.notNull(book.getId() ,"参数book中id字段不能为空");
        if (book.getPrice() == null) return ;   // 作为测试，本例目前仅支持price字段更新(实际书名、ISBN也应该是不能更新的)
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                StringBuilder builder = new StringBuilder("UPDATE TBL_BOOK SET ") ;
                if (book.getPrice() != null) builder.append("PRICE = ? ") ;
                builder.append("WHERE ID = ?") ;
                PreparedStatement statement = connection.prepareStatement(builder.toString()) ;
                int index = 0 ;
                if (book.getPrice() != null) statement.setFloat(++ index ,book.getPrice());
                statement.setLong(++ index ,book.getId());
                return statement;
            }
        }) ;
    }

}
