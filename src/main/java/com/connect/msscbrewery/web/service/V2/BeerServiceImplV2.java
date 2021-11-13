package com.connect.msscbrewery.web.service.V2;


import com.connect.msscbrewery.web.model.BeerDto;
import com.connect.msscbrewery.web.model.V2.BeerDTOV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BeerServiceImplV2 implements BeerServiceV2 {
    @Override
    public BeerDTOV2 getBeerById(UUID id) {
        return BeerDTOV2.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat").beerStyle("ale").build();
    }

    @Override
    public BeerDTOV2 addBeer(BeerDTOV2 beerDto) {
        return BeerDTOV2.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDTOV2 beerDto) {

    }

    @Override
    public void deleteById(String beerId) {
        log.debug("Beer deleted with"+ beerId);
    }
}
