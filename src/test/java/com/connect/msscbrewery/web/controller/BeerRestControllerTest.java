package com.connect.msscbrewery.web.controller;
import com.connect.msscbrewery.web.controller.V2.BeerRestControllerV2;
import com.connect.msscbrewery.web.model.BeerDto;
import com.connect.msscbrewery.web.model.V2.BeerDTOV2;
import com.connect.msscbrewery.web.service.V2.BeerServiceV2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerRestControllerV2.class)
@ComponentScan(basePackages = "package com.connect.msscbrewery.domain.Mapper")
class BeerRestControllerTest {
        @Autowired
        MockMvc mockMvc;

        @MockBean
        BeerServiceV2 service;

        @Autowired
        ObjectMapper objectMapper;

        BeerDTOV2 validBeer;
        @BeforeEach
        public void setUp()
        {
            validBeer= BeerDTOV2.builder().id(UUID.randomUUID()).beerStyle("ale").beerName("heniken").build();
        }


        @Test
        void getBeerById() throws Exception {
            mockMvc.perform(get("/api/v2/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        void saveNewBeer() throws Exception {
            BeerDTOV2 beerDTOV2=validBeer; beerDTOV2.setId(null);
            BeerDTOV2 savedDTO= BeerDTOV2.builder().id(UUID.randomUUID()).beerName("New Beer").beerStyle("bitter").build();
            String jsonString = objectMapper.writeValueAsString(beerDTOV2);

            given(service.addBeer(any())).willReturn(savedDTO);


            mockMvc.perform(post("/api/v2/beer").content(jsonString).accept(MediaType.APPLICATION_JSON)
                            .header("Content-Type","application/json"))
                    .andExpect(status().isCreated());
        }

        @Test
        void updateBeer() throws Exception{
            BeerDto beerDto= BeerDto.builder().beerName("abc").beerStyle("bitter").build();
            String jsonString = objectMapper.writeValueAsString(beerDto);
            mockMvc.perform(put("/api/v2/beer/"+ UUID.randomUUID().toString()).content(jsonString).accept(MediaType.APPLICATION_JSON)
                            .header("Content-Type","application/json"))
                    .andExpect(status().isNoContent());
        }

        @Test
        void deleteById() throws Exception {
            mockMvc.perform(delete("/api/v2/beer/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }
    }
