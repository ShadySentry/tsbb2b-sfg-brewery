package guru.springframework.brewery.web.controllers;

import guru.springframework.brewery.web.model.BeerPagedList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@WebMvcTest
public class BeerOrderControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testListOrders() {
        BeerPagedList pagedList = testRestTemplate.getForObject("")
    }
}
