package com.library.management.entity;

import com.library.management.dto.CustomerDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "customer_book_owned",
            joinColumns =
            @JoinColumn(name = "customer_id",
                        referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "book_id",
                        referencedColumnName = "id")
    )
    private List<Book> bookList;

    private Integer bookCount;

    public Customer(int id) {
        this.id = id;
    }

    public Customer(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.bookCount = customerDto.getBookCount();
        this.name = customerDto.getName();
        this.bookList = customerDto.getBookDtoList().stream()
                .map(bookDto -> new Book(bookDto))
                .collect(Collectors.toList());
    }
}
