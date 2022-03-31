package com.library.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.library.management.entity.BookingHist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingHistDto {

    private int id;

    private BookDto bookDto;

    private CustomerDto customerDto;

    private LocalDate bookingDate;

    private LocalDate returnDate;

    public BookingHistDto(BookingHist bookingHist) {
        this.id = bookingHist.getId();
        this.bookDto = new BookDto(bookingHist.getBook());
        this.customerDto = new CustomerDto(bookingHist.getCustomer());
        this.bookingDate = bookingHist.getBookingDate();
        this.returnDate = bookingHist.getReturnDate();
    }
}
