package com.library.management.controller;


import com.library.management.repository.OrderRepository;
import com.library.management.service.BookService;
import com.library.management.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void orderBookFromLibraryTest() throws Exception {
        mockMvc.perform(get("/api/v1/order/1/1"))
                .andExpect(status().is2xxSuccessful());

        verify(orderService).orderBookFromLibrary(1, 1);
    }

    @Test
    public void returnBookToLibraryTest() throws Exception {
        mockMvc.perform(get("/api/v1/order/return/1/1"))
                .andExpect(status().is2xxSuccessful());

        verify(orderService).returnBookToLibrary(1, 1);
    }
}
