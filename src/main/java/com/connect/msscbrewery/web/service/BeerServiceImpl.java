package com.connect.msscbrewery.web.service;

import com.connect.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BeerServiceImpl implements  BeerService{
    @Override
    public BeerDto getBeerById() {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pal").build();
    }

    @Override
    public BeerDto addBeer(BeerDto beerDto) {
        return BeerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {

    }

    @Override
    public void deleteById(String beerId) {
        log.debug("Beer deleted with"+ beerId);
    }
}
