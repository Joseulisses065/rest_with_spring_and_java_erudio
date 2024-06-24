package com.rest_with_spring_and_java_erudio.service;

import com.rest_with_spring_and_java_erudio.data.mapper.VOModelMapper;
import com.rest_with_spring_and_java_erudio.data.vo.v1.BookVO;
import com.rest_with_spring_and_java_erudio.domain.entity.Book;
import com.rest_with_spring_and_java_erudio.repository.BookRepository;
import com.rest_with_spring_and_java_erudio.web.exception.EntityNotFoundException;
import com.rest_with_spring_and_java_erudio.web.exception.RequiredObjectIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class BookServices {
    @Autowired
    private BookRepository bookRepository;
    private final AtomicLong count = new AtomicLong();
    private Logger logger = Logger.getLogger(BookServices.class.getName());


    public BookVO findById(Long id) {
        logger.info("finding book");
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Book with id {%s} not found", id))
        );
        return VOModelMapper.parseObject(book, BookVO.class);
    }

    public List<BookVO> findAll() {
        logger.info("finding all books");
        return VOModelMapper.parseListObjects(bookRepository.findAll(),BookVO.class);
    }

    public BookVO create(BookVO book) {
        if (book == null)throw new RequiredObjectIsNullException();
        Book entity = bookRepository.save(VOModelMapper.parseObject(book, Book.class));
        return VOModelMapper.parseObject(entity, BookVO.class);
    }

    public BookVO update(Long id, BookVO book) {
        if (book == null)throw new RequiredObjectIsNullException();
        Book entity = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user of you want update not found")
        );
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        Book personResponse =  bookRepository.save(VOModelMapper.parseObject(entity,Book.class));
        return VOModelMapper.parseObject(personResponse,BookVO.class);
    }

    public BookVO delete(Long id) {
        Book entity = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("book of you want delete not found")
        );
        logger.info("Deleting book");
        bookRepository.delete(entity);
        return VOModelMapper.parseObject(entity,BookVO.class);
    }
    
}
