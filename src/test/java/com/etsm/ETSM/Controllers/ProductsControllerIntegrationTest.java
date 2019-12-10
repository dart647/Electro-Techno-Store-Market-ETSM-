package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

class ProductsControllerIntegrationTest {
        @Autowired
        MockMvc mockMvc;

        @SpyBean
        ProductRepository productRepository;

        @Test
        public void list_ReturnsProductsListPage() throws Exception {

        }


    @Test
    void list() {
    }

    @Test
    void product() {
    }
}