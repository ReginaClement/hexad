package com.library.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.library.management.entity.Book;
import com.library.management.entity.Customer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {

    private int id;

    private String name;

    private List<BookDto> bookDtoList;

    private Integer bookCount;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.bookDtoList = customer.getBookList().stream()
                .map(book -> new BookDto(book)).collect(Collectors.toList());
        this.bookCount = customer.getBookCount();
        this.name = customer.getName();
    }
}
