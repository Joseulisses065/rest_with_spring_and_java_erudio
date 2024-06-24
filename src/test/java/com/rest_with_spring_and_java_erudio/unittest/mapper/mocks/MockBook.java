package com.rest_with_spring_and_java_erudio.unittest.mapper.mocks;

import com.rest_with_spring_and_java_erudio.data.vo.v1.BookVO;
import com.rest_with_spring_and_java_erudio.data.vo.v1.PersonVO;
import com.rest_with_spring_and_java_erudio.domain.entity.Book;
import com.rest_with_spring_and_java_erudio.domain.entity.Person;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setTitle("test title"+number);
        book.setLaunchDate(LocalDateTime.now());
        book.setPrice(new BigDecimal(2*number));
        book.setAuthor("test author "+number);
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setId(number.longValue());
        book.setTitle("test title"+number);
        book.setLaunchDate(LocalDateTime.now());
        book.setPrice(new BigDecimal(2*number));
        book.setAuthor("test author "+number);
        return book;
    }
}
