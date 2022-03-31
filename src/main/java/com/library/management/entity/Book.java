package com.library.management.entity;

import com.library.management.dto.BookDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "current_count")
    private Long currentCount;

    @Column(name = "total_count")
    private Long totalCount;


    public Book() {}

    public Book(int id) {
        this.id = id;
    }

    public Book(BookDto bookDto) {
        this.id = bookDto.getId();
        this.bookName = bookDto.getBookName();
        this.author = bookDto.getAuthor();
        this.description = bookDto.getDescription();
        this.purchaseDate = bookDto.getPurchaseDate();
        this.currentCount = bookDto.getCurrentCount();
        this.totalCount = bookDto.getTotalCount();
    }
}
