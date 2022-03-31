package com.library.management.service;

import com.library.management.dto.BookingHistDto;

import java.util.Optional;

public interface OrderService {
    Optional<BookingHistDto> orderBookFromLibrary(Integer bookId, Integer empId) throws Exception;

    Optional<BookingHistDto> returnBookToLibrary(Integer bookId, Integer customerId) throws Exception;
}
