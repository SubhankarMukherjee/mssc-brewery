package com.connect.msscbrewery.domain.Mapper;

import com.connect.msscbrewery.domain.Beer;
import com.connect.msscbrewery.web.model.BeerDto;
import com.connect.msscbrewery.web.model.V2.BeerDTOV2;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDTOV2 convertBeerToBeerDTOV2(Beer beer);

    Beer covertBeerDTO2ToBeer(BeerDTOV2 beerDTOV2);
}
