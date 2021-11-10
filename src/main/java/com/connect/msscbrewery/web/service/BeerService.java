package com.connect.msscbrewery.web.service;


import com.connect.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {

    public BeerDto getBeerById();

    BeerDto addBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

    void deleteById(String beerId);
}

