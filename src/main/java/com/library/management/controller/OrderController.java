package com.library.management.controller;

import com.library.management.dto.BookingHistDto;
import com.library.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{bookId}/{customerId}")
    public Optional<BookingHistDto> orderBookFromLibrary(@PathVariable Integer bookId, @PathVariable Integer customerId) throws Exception{
        return orderService.orderBookFromLibrary(bookId, customerId);
    }

    @GetMapping("/return/{bookId}/{customerId}")
    public Optional<BookingHistDto> returnBookToLibrary(@PathVariable Integer bookId, @PathVariable Integer customerId) throws Exception {
        return orderService.returnBookToLibrary(bookId, customerId);
    }

}
