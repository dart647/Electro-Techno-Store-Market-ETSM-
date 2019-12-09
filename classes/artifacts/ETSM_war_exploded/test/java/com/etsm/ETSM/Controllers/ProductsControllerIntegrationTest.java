package com.etsm.ETSM.Controllers;

import com.etsm.ETSM.Models.Product;
import com.etsm.ETSM.Models.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.UUID;


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
            this.productRepository.getProducts().clear();
            // given
            ArrayList<Product> products = new ArrayList<>();
            for (int i = 1; i < 4; i++) {
                products.add(new Product(UUID.randomUUID(), "Product №" + i, "Desc"));
            }

            this.productRepository.getProducts().addAll(products);

            // when
            this.mockMvc.perform(MockMvcRequestBuilders.get("/catalog/list"))
                    // then
                    // проверим, что статус 200
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    // модель содержит список товаров
                    .andExpect(MockMvcResultMatchers.model().attribute("products", products))
                    // имя шаблона соответствует ожидаемому
                    .andExpect(MockMvcResultMatchers.view().name("/catalog/list"))
                    // на странице есть три элемента <li> с аттрибутом class='product"
                    .andExpect(MockMvcResultMatchers.xpath("//li[@class='product']").nodeCount(3));

            Mockito.verify(this.productRepository).findAll();
        }


    @Test
    void list() {
    }

    @Test
    void product() {
    }
}