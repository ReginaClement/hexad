package com.library.management.service;

import com.library.management.dto.BookDto;

import java.util.List;

public interface BookService {
    void book();

    BookDto save(BookDto bookDto);

    List<BookDto> getAllBooks();
}
