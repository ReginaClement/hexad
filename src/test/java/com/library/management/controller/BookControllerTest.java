package com.library.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.dto.BookDto;
import com.library.management.entity.Book;
import com.library.management.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@ExtendWith(SpringExtension.class)

@TestPropertySource(locations = "classpath:test.properties")
//@AutoConfigureMockMvc
//@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookService bookService;


    @Test
    void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setId(1);

        given(bookService.getAllBooks())
                .willReturn(Arrays.asList(new BookDto(book)));

        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[*].id").value(1));

        verify(bookService).getAllBooks();
    }


    @Test
    void testSave() throws Exception {
        BookDto bookDto = new BookDto();

        given(bookService.save(bookDto))
                .willReturn(bookDto);

        mockMvc.perform(post("/api/v1/books/book")
                        .contentType("application/json")
                        .content(asJsonString(bookDto))
                )
                .andExpect(status().is2xxSuccessful());

        verify(bookService).save(bookDto);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
