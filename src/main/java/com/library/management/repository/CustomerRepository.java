package com.library.management.repository;

import com.library.management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    long deleteByBookList_Id(int id);
}
