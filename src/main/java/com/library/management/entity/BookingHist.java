package com.library.management.entity;

import com.library.management.dto.BookingHistDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking_history")
@Data
@NoArgsConstructor
public class BookingHist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "return_data")
    private LocalDate returnDate;

    public BookingHist(Book book, Customer customer, LocalDate bookingDate) {
        this.book = book;
        this.customer = customer;
        this.bookingDate = bookingDate;
    }

    public BookingHist(BookingHistDto bookingHistDto) {
        this.id = bookingHistDto.getId();
        this.book = new Book(bookingHistDto.getBookDto());
        this.customer = new Customer(bookingHistDto.getCustomerDto());
        this.bookingDate = bookingHistDto.getBookingDate();
        this.returnDate = bookingHistDto.getReturnDate();
    }

}
