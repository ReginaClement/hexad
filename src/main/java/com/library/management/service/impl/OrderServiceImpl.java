package com.library.management.service.impl;

import com.library.management.dto.BookingHistDto;
import com.library.management.entity.Book;
import com.library.management.entity.BookingHist;
import com.library.management.entity.Customer;
import com.library.management.repository.BookRepository;
import com.library.management.repository.CustomerRepository;
import com.library.management.repository.OrderRepository;
import com.library.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;


    @Override
    @Transactional
    public Optional<BookingHistDto> orderBookFromLibrary(Integer bookId, Integer custId) throws Exception {
        if (isNotAlreadyBorrowed(bookId, custId) && isBookAvailableInLibrary(bookId)) {
            if (takeABookFromLibrary(bookId)) {
                if (giveTheBookToTheCustomer(bookId, custId)) {
                    return Optional.of(new BookingHistDto(addBookingHistory(bookId, custId)));
                }
            }
        }
        return Optional.empty();
    }

    public Boolean isNotAlreadyBorrowed(Integer bookId, Integer custId) throws Exception {
        Optional<Customer> customer = customerRepository.findById(custId);
        if (customer.isPresent()) {
            Customer cust = customer.get();
            List<Book> bookList = cust.getBookList();
            if (bookList.stream().anyMatch(book -> book.getId() == bookId)) {
                throw new Exception("Already borrowed same book.");
            }
        }
        return Boolean.TRUE;
    }


    public Boolean isBookAvailableInLibrary(Integer bookId) throws Exception{
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isPresent()) {
            if (book.get().getCurrentCount() > 0) {
                return Boolean.TRUE;
            } else {
                throw new Exception("Book is not available in the library.");
            }
        }

        return Boolean.FALSE;
    }

    public Boolean takeABookFromLibrary(Integer bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isPresent()) {
            Book currentBook = book.get();
            long currentCount = currentBook.getCurrentCount() - 1;
            currentBook.setCurrentCount(currentCount);
            bookRepository.save(currentBook);
        }

        return Boolean.TRUE;
    }

    public Boolean giveTheBookToTheCustomer(Integer bookId, Integer custId) throws Exception {
        Optional<Customer> customer = customerRepository.findById(custId);

        if (customer.isPresent()) {
            Customer cust = customer.get();
            if (cust.getBookCount() >= 2) {
                throw new Exception("Limit exceeded.");
            }
            List<Book> bookList = cust.getBookList();
            bookList.add(new Book(bookId));
            cust.setBookList(bookList);
            cust.setBookCount(cust.getBookCount() + 1);
            customerRepository.save(cust);
        }

        return Boolean.TRUE;
    }

    private BookingHist addBookingHistory(Integer bookId, Integer custId) {
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Customer> customer = customerRepository.findById(custId);
        BookingHist bookingHist = new BookingHist(book.get(), customer.get(), LocalDate.now());
        return orderRepository.save(bookingHist);
    }


    @Override
    @Transactional
    public Optional<BookingHistDto> returnBookToLibrary(Integer bookId, Integer customerId) throws Exception {
        getTheBookFromCustomer(bookId, customerId);
        placeTheBookBackInLibrary(bookId);
        return updateTheBookingHistory(bookId, customerId);
    }


    public void getTheBookFromCustomer(Integer bookId, Integer customerId) throws Exception {
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            Customer currentCustomer = customer.get();
            List<Book> bookList = currentCustomer.getBookList().stream()
                    .filter(book -> book.getId() != bookId)
                    .collect(Collectors.toList());

            currentCustomer.setBookList(bookList);
            currentCustomer.setBookCount(currentCustomer.getBookCount() - 1);
            customerRepository.save(currentCustomer);
        } else {
            throw new Exception("No customer found for "+ customerId);
        }
    }

    private void placeTheBookBackInLibrary(Integer bookId) {
        Optional<Book> borrowedBook = bookRepository.findById(bookId);
        if (borrowedBook.isPresent()) {
            Book book = borrowedBook.get();
            book.setCurrentCount(book.getCurrentCount() + 1);

            bookRepository.save(book);
        }
    }


    private Optional<BookingHistDto> updateTheBookingHistory(Integer bookId, Integer customerId) throws Exception {
        Optional<BookingHist> borrowedHistory = Optional.ofNullable(orderRepository
                .findByBook_IdAndCustomer_IdAndReturnDate(bookId, customerId, null));

        if(borrowedHistory.isPresent()) {
            BookingHist bookingHist = borrowedHistory.get();
            bookingHist.setReturnDate(LocalDate.now());
            BookingHist updatedBookHistory = orderRepository.save(bookingHist);
            return Optional.of(new BookingHistDto(updatedBookHistory));
        } else {
            throw new Exception("No book available to return");
        }
    }
}
