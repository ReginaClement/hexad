package com.library.management.service;

import com.library.management.dto.BookDto;
import com.library.management.entity.Book;
import com.library.management.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private  BookRepository bookRepository;

   // @BeforeEach
    public void init() {
        BookDto bookDto = new BookDto();
        bookRepository.save(new Book(bookDto));
    }

    @Test
    public void saveTest() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setBookName("");
        bookDto.setCurrentCount(1L);
        bookDto.setAuthor("");
        bookDto.setDescription("");

        Mockito.when(bookRepository
                .save(new Book(bookDto)))
                .thenReturn(new Book(bookDto));

        BookDto savedBook = bookService.save(bookDto);
        assertNotNull(savedBook);
    }

    @Test
    public void getAllBooksTest() {
        List<BookDto> allBooks = bookService.getAllBooks();
        System.out.println(allBooks);
        assertNotNull(
                allBooks
        );

    }
}


