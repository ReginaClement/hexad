package com.library.management.repository;

import com.library.management.entity.BookingHist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<BookingHist, Integer> {

    List<BookingHist> findByCustomer_Id(int id);

    BookingHist findByBook_IdAndCustomer_Id(int bookId, int customerId);

    BookingHist findByBook_IdAndCustomer_IdAndReturnDate(int bookId, int customerId, LocalDate returnDate);



}
