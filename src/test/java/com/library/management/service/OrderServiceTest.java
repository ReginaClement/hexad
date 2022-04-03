package com.library.management.service;

import com.library.management.entity.Book;
import com.library.management.entity.Customer;
import com.library.management.repository.BookRepository;
import com.library.management.repository.CustomerRepository;
import com.library.management.repository.OrderRepository;
import com.library.management.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations="classpath:test.properties")
public class OrderServiceTest {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private BookRepository bookRepository;


    @Test
    public void isNotAlreadyBorrowedTest() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        Book book = new Book();
        book.setId(2);
        customer.setBookList(Arrays.asList(book));

        Mockito.when(customerRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(customer));
        Boolean notAlreadyBorrowed = orderServiceImpl
                .isNotAlreadyBorrowed(1, 1);

        assertEquals(notAlreadyBorrowed, Boolean.TRUE);
    }

    @Test
    public void alreadyBorrowedTest() throws Exception {
        try {
            Customer customer = new Customer();
            customer.setId(1);
            Book book = new Book();
            book.setId(1);
            customer.setBookList(Arrays.asList(book));

            Mockito.when(customerRepository.findById(Mockito.anyInt()))
                    .thenReturn(Optional.ofNullable(customer));
            Boolean notAlreadyBorrowed = orderServiceImpl
                    .isNotAlreadyBorrowed(1, 1);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Already borrowed same book.");
        }
    }

    @Test
    public void isBookAvailableInLibraryTest() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setCurrentCount(2L);

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.ofNullable(book));
        assertEquals(orderServiceImpl.isBookAvailableInLibrary(1),Boolean.TRUE);
    }

    @Test
    public void isBookNotAvailableInLibraryTest() throws Exception {
        try {
            Book book = new Book();
            book.setId(1);
            book.setCurrentCount(0L);

            Mockito.when(bookRepository.findById(1)).thenReturn(Optional.ofNullable(book));
            orderServiceImpl.isBookAvailableInLibrary(1);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Book is not available in the library.");
        }
    }

    @Test
    public void takeABookFromLibraryTest() throws Exception {

        Book book = new Book();
        book.setId(1);
        book.setCurrentCount(2L);

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.save(book)).thenReturn(book);

        assertEquals(orderServiceImpl.takeABookFromLibrary(1), Boolean.TRUE);
    }

    @Test
    public void getTheBookFromCustomerTest() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        Book book = new Book();
        book.setId(1);
        book.setCurrentCount(2L);
        customer.setBookList(Arrays.asList(book));
        customer.setBookCount(2);

        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.ofNullable(customer));
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        orderServiceImpl.getTheBookFromCustomer(2, 1);
    }
}
