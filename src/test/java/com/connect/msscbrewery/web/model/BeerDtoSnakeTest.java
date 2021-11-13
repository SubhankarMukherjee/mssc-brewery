package com.connect.msscbrewery.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("snake")
@JsonTest
public class BeerDtoSnakeTest extends BaseTest{

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void serializeJson() throws JsonProcessingException {
        BeerDto beerDto=getBeerDTO();
        String jsonString=objectMapper.writeValueAsString(getBeerDTO());
        System.out.println("JSON String is :"+ jsonString);
    }

}