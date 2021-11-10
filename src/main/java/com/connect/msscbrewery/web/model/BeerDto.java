package com.connect.msscbrewery.web.model;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class BeerDto {

    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;

}
