package com.library.management.service.impl;

import com.library.management.dto.BookDto;
import com.library.management.entity.Book;
import com.library.management.repository.BookRepository;
import com.library.management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void book() {
        System.out.println("Service book");
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Book save = bookRepository.save(new Book(bookDto));
        return new BookDto(save);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDto::new)
                .collect(Collectors.toList());
    }
}
