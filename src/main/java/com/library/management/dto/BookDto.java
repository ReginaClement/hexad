package com.library.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.library.management.entity.Book;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

    private int id;

    private String bookName;

    private String author;

    private String description;

    private LocalDate purchaseDate;

    private Long currentCount;

    private Long totalCount;

    public BookDto() {}

    public BookDto(Book book) {
        this.id = book.getId();
        this.bookName = book.getBookName();
        this.author = book.getAuthor();
        this.description = book.getDescription();
        this.purchaseDate = book.getPurchaseDate();
        this.currentCount = book.getCurrentCount();
        this.totalCount = book.getTotalCount();
    }
}
