package com.zlikun.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/7/10 10:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id ;
    private String name ;
    private String isbn ;
    private Float price ;
    private Date ctime ;

}
