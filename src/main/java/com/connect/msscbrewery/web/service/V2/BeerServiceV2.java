package com.connect.msscbrewery.web.service.V2;


import com.connect.msscbrewery.web.model.V2.BeerDTOV2;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDTOV2 getBeerById();

    BeerDTOV2 addBeer(BeerDTOV2 beerDTOV2);

    void updateBeer(UUID beerId, BeerDTOV2 beerDTOV2);

    void deleteById(String beerId);
}
