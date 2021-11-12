package com.connect.msscbrewery.domain;

import com.connect.msscbrewery.web.model.BeerDto;
import com.connect.msscbrewery.web.model.V2.BeerDTOV2;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    BeerDTOV2 convertBeerToBeerDTOV2(Beer beer);

    Beer covertBeerDTO2ToBeer(BeerDTOV2 beerDTOV2);
}
