package guru.springframework.brewery.web.controllers;

import guru.springframework.brewery.services.BeerOrderService;
import guru.springframework.brewery.web.model.BeerOrderPagedList;
import guru.springframework.brewery.web.model.BeerPagedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.MapKeyColumn;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerOrderController.class)
class BeerOrderControllerTest {

    @MockBean
    BeerOrderService orderService;

    @Autowired
    MockMvc mockMvc;

    BeerOrderPagedList beerPagedList;

    @BeforeEach
    void setUp(){
        //TODO create BeerOrderPagedList
    }

    @AfterEach
    void tearDown() {
        reset(orderService);
    }

    @Test
    void listOrders() throws Exception {
        given(orderService.listOrders(any(),any())).willReturn(beerPagedList);
        int id=1;

        MvcResult result = mockMvc.perform(get("/api/v1/customers/"+id+"/orders"))
                .andExpect(status().isOk()).andReturn();
        System.out.println(result);
    }

    @Test
    void getOrder() {
    }
}