package com.connect.msscbrewery.web.model.V2;

import com.connect.msscbrewery.web.model.BeerDto;
import lombok.*;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class BeerDTOV2 {

    private UUID id;
    private String beerName;
    private BeerDto.BeerStyleEnum beerStyle;
    private Long upc;

}