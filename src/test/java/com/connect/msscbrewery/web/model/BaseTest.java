package com.connect.msscbrewery.web.model;

import com.connect.msscbrewery.domain.Beer;

public class BaseTest {

    public BeerDto getBeerDTO()
    {
        return BeerDto.builder().beerName("Heniken")
                .beerStyle("IPA").upc(1234234783l)
                .build();
    }
}
