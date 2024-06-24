package com.rest_with_spring_and_java_erudio.unittest.mapper.unittest.mokito;

import com.rest_with_spring_and_java_erudio.data.vo.v1.BookVO;
import com.rest_with_spring_and_java_erudio.data.vo.v1.PersonVO;
import com.rest_with_spring_and_java_erudio.domain.entity.Book;
import com.rest_with_spring_and_java_erudio.repository.BookRepository;
import com.rest_with_spring_and_java_erudio.service.BookServices;
import com.rest_with_spring_and_java_erudio.unittest.mapper.mocks.MockBook;
import com.rest_with_spring_and_java_erudio.unittest.mapper.mocks.MockPerson;
import com.rest_with_spring_and_java_erudio.web.exception.RequiredObjectIsNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    MockBook input;

    @InjectMocks
    private BookServices bookServices;
    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testFindById() {
        Book Book = input.mockEntity(1);
        Book.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(Book));

        var result = bookServices.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals("test title1", result.getTitle());
        assertEquals(new BigDecimal(2*1), result.getPrice());
        assertEquals("test author 1",result.getAuthor());
        assertNotNull(result.getLaunchDate());

    }

    @Test
    void create() {
        Book persisted = input.mockEntity(1);
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setId(1L);

        when(bookRepository.save(any(Book.class))).thenReturn(persisted);

        var result = bookServices.create(vo);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals("test title1", result.getTitle());
        assertEquals(new BigDecimal(2*1), result.getPrice());
        assertEquals("test author 1",result.getAuthor());
        assertNotNull(result.getLaunchDate());

    }

    @Test
    void testUpdate() {

        Book Book = input.mockEntity(1);
        Book persisted = Book;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);

        vo.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(Book));
        when(bookRepository.save(Book)).thenReturn(persisted);

        var result = bookServices.update(1L, vo);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals("test title1", result.getTitle());
        assertEquals(new BigDecimal(2*1), result.getPrice());
        assertEquals("test author 1",result.getAuthor());
        assertNotNull(result.getLaunchDate());

    }

    @Test
    void testDelete() {
        Book Book = input.mockEntity(1);
        Book.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(Book));

        bookServices.delete(1L);
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    bookServices.create(null);
                });
        String expecterMessag = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expecterMessag));


    }

    @Test
    void testUpdateeWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    bookServices.update(null, null);
                });
        String expecterMessag = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expecterMessag));


    }

    @Test
    void testFindAll() {
        List<Book> books = input.mockEntityList();

        when(bookRepository.findAll()).thenReturn(books);

        var result = bookServices.findAll();
        assertNotNull(result);
        assertEquals(14,books.size());
        var booksOne = result.get(1);

        assertNotNull(booksOne);
        assertNotNull(booksOne.getId());
        assertNotNull(booksOne.getLinks());
        assertEquals("test title1", booksOne.getTitle());
        assertEquals(new BigDecimal(2*1), booksOne.getPrice());
        assertEquals("test author 1",booksOne.getAuthor());
        assertNotNull(booksOne.getLaunchDate());

        var bookFour = result.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());
        assertEquals("test title4", bookFour.getTitle());
        assertEquals(new BigDecimal(2*4), bookFour.getPrice());
        assertEquals("test author 4",bookFour.getAuthor());
        assertNotNull(bookFour.getLaunchDate());

        var bookSeven = result.get(7);
        System.out.println(bookSeven.getLinks());
        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getId());
        assertNotNull(bookSeven.getLinks());
        assertEquals("test title7", bookSeven.getTitle());
        assertEquals(new BigDecimal(2*7), bookSeven.getPrice());
        assertEquals("test author 7",bookSeven.getAuthor());
        assertNotNull(bookSeven.getLaunchDate());

        //assertTrue(result.toString().);
    }
}
