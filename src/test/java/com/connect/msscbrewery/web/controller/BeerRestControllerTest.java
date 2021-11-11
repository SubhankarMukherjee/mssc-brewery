package com.connect.msscbrewery.web.controller;
import com.connect.msscbrewery.web.model.BeerDto;
import com.connect.msscbrewery.web.service.BeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerRestController.class)
class BeerRestControllerTest {
        @Autowired
        MockMvc mockMvc;

        @MockBean
    BeerService service;

        @Autowired
        ObjectMapper objectMapper;

        BeerDto validBeer;
        @BeforeEach
        public void setUp()
        {
            validBeer= BeerDto.builder().id(UUID.randomUUID()).beerStyle("ale").beerName("heniken").build();
        }


        @Test
        void getBeerById() throws Exception {
            mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        void saveNewBeer() throws Exception {
            BeerDto beerDto=validBeer; beerDto.setId(null);
            BeerDto savedDTO= BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").beerStyle("bitter").build();
            String jsonString = objectMapper.writeValueAsString(beerDto);

            given(service.addBeer(any())).willReturn(savedDTO);
            System.out.println(beerDto);
            System.out.println(savedDTO.toString());
            BeerDto beerDto1 = service.addBeer(beerDto);
            mockMvc.perform(post("/api/v1/beer").content(jsonString).accept(MediaType.APPLICATION_JSON)
                            .header("Content-Type","application/json"))
                    .andExpect(status().isCreated());
        }

        @Test
        void updateBeer() throws Exception{
            BeerDto beerDto= BeerDto.builder().beerName("abc").beerStyle("bitter").build();
            String jsonString = objectMapper.writeValueAsString(beerDto);
            mockMvc.perform(put("/api/v1/beer/"+ UUID.randomUUID().toString()).content(jsonString).accept(MediaType.APPLICATION_JSON)
                            .header("Content-Type","application/json"))
                    .andExpect(status().isNoContent());
        }

        @Test
        void deleteById() throws Exception {
            mockMvc.perform(delete("/api/v1/beer/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }
    }
