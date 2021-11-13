package com.connect.msscbrewery.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
public class BeerDtoTest extends BaseTest{

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void serializeJson() throws JsonProcessingException {
        BeerDto beerDto=getBeerDTO();
        String jsonString=objectMapper.writeValueAsString(getBeerDTO());
        System.out.println("JSON String is :"+ jsonString);
    }
    @Test
    public void deSerializeJson() throws JsonProcessingException {

        String jsonString="{\"id\":null,\"beerName\":\"Heniken\",\"beerStyle\":\"IPA\",\"upc\":1234234783}";
        BeerDto beerDto = objectMapper.readValue(jsonString, BeerDto.class);
        System.out.println("Beer Object is :"+ beerDto.toString());
    }
}