package com.arinahitech.planner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.arinahitech.planner")
public class AppConfig {

    @Bean("bookReview")
    @Scope("prototype")
    public BookReview getBookReview() {
        BookReview bookReview = new BookReview();
        bookReview.setBook(new Book("Anna Kov"));
        return bookReview;
    }

}
