package br.com.kkrbeerservice.web.controller;

import br.com.kkrbeerservice.repositories.BeerRepository;
import br.com.kkrbeerservice.services.beer.BeerService;
import br.com.kkrbeerservice.web.model.BeerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    @MockBean
    BeerRepository beerRepository;

    @Autowired
    ObjectMapper objectMapper;

    private BeerDto create() {
        return BeerDto.builder()
                .beerName("Custom Beer")
                .beerStyle(BeerDto.Style.ALE)
                .price(new BigDecimal("2.99"))
                .upc("07891149102150")
                .build();
    }


    @Test
    void getBeer() throws Exception {
        String uri = String.format("/api/v1/beer/%s", UUID.randomUUID());
        mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createBeer() throws Exception {
        BeerDto beerDto = create();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    void updateBeer() throws Exception {
        String uri = String.format("/api/v1/beer/%s", UUID.randomUUID());
        BeerDto beerDto = create();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBeer() throws Exception {
        String uri = String.format("/api/v1/beer/%s", UUID.randomUUID());
        mockMvc.perform(delete(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}