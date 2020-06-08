package guru.springframework.brewery.web.controllers;

import guru.springframework.brewery.services.BeerOrderService;
import guru.springframework.brewery.web.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
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

    BeerDto validBeer;
    BeerOrderDto beerOrder;
    BeerOrderPagedList beerPagedList;

    @BeforeEach
    void setUp() {
        validBeer = BeerDto.builder().id(UUID.randomUUID())
                .version(1)
                .beerName("Beer1")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal("12.99"))
                .quantityOnHand(4)
                .upc(123456789012L)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .build();

        beerOrder = BeerOrderDto.builder()
                .id(UUID.randomUUID())
//                .version(1)
//                .createdDate(OffsetDateTime.now())
//                .lastModifiedDate(OffsetDateTime.now())
                .customerRef("1234")
                .beerOrderLines(List.of(BeerOrderLineDto.builder()
                        .beerId(validBeer.getId())
                        .build())).
                build();

        beerPagedList = new BeerOrderPagedList(List.of(beerOrder), PageRequest.of(1,1),1L);
    }

    @AfterEach
    void tearDown() {
        reset(orderService);
    }

    @Test
    void listOrders() throws Exception {
        given(orderService.listOrders(any(), any())).willReturn(beerPagedList);
        UUID id = UUID.randomUUID();
        MvcResult result = mockMvc.perform(get("/api/v1/customers/" + id + "/orders"))
                .andExpect(status().isOk()).andReturn();
        System.out.println(result);
    }

    @Test
    void getOrder() {
    }
}