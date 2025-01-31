package com.capitole.pricinginditex;

import com.capitole.pricinginditex.application.service.PriceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PricinginditexApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Mock
    private PriceService priceService;


    @Test
    void test1() throws Exception {
        int productId = 35455;
        int brandId = 1;
        String applicationDate = "2020-06-14T10:00:00";

        mockMvc.perform(get("/prices")
                        .param("date", applicationDate)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.startDate").isNotEmpty())
                .andExpect(jsonPath("$.endDate").isNotEmpty())
                .andExpect(jsonPath("$.price").isNumber());
    }

    @Test
    void test2() throws Exception {
        int productId = 35455;
        int brandId = 1;
        String applicationDate = "2020-06-14T16:00:00";

        mockMvc.perform(get("/prices")
                        .param("date", applicationDate)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.startDate").isNotEmpty())
                .andExpect(jsonPath("$.endDate").isNotEmpty())
                .andExpect(jsonPath("$.price").isNumber());
    }

    @Test
    void test3() throws Exception {
        int productId = 35455;
        int brandId = 1;
        String applicationDate = "2020-06-21T10:00:00";

        mockMvc.perform(get("/prices")
                        .param("date", applicationDate)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.startDate").isNotEmpty())
                .andExpect(jsonPath("$.endDate").isNotEmpty())
                .andExpect(jsonPath("$.price").isNumber());
    }

    @Test
    void test4() throws Exception {
        int productId = 35455;
        int brandId = 1;
        String applicationDate = "2020-06-15T10:00:00";

        mockMvc.perform(get("/prices")
                        .param("date", applicationDate)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.startDate").isNotEmpty())
                .andExpect(jsonPath("$.endDate").isNotEmpty())
                .andExpect(jsonPath("$.price").isNumber());
    }

    @Test
    void test5() throws Exception {
        int productId = 35455;
        int brandId = 1;
        String applicationDate = "2020-06-16T21:00:00";

        mockMvc.perform(get("/prices")
                        .param("date", applicationDate)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.startDate").isNotEmpty())
                .andExpect(jsonPath("$.endDate").isNotEmpty())
                .andExpect(jsonPath("$.price").isNumber());
    }

    @Test
    void testNotFound() throws Exception {
        int productId = 99999; // ID inexistente
        int brandId = 99;
        String applicationDate = "2020-06-16T21:00:00";

        mockMvc.perform(get("/prices")
                        .param("date", applicationDate)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.error").value("No price found for product 99999, brand 99 on date 2020-06-16T21:00"));
    }

    @Test
    void testInternalServerError() throws Exception {
        int productId = 35455;
        String applicationDate = "2020-06-16T21:00:00";

        when(priceService.getApplicablePrice(anyInt(), anyInt(), any()))
                .thenThrow(new RuntimeException("Unexpected error occurred"));

        mockMvc.perform(get("/prices")
                        .param("date", applicationDate)
                        .param("productId", String.valueOf(productId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.error").value("Required request parameter 'brandId' for method parameter type Integer is not present"));
    }

    @Test
    void testConnectionToH2() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        assertNotNull(result);
        assertEquals(1, result);
    }
}