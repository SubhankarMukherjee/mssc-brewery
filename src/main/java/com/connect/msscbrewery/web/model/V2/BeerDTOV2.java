package com.connect.msscbrewery.web.model.V2;

import com.connect.msscbrewery.web.model.BeerDto;
import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class BeerDTOV2 {
    @Null
    private UUID id;
    @NotBlank
    private String beerName;
    @NotBlank
    private String beerStyle;
    @Positive
    private Long upc;

    private OffsetDateTime creationDate;

    private OffsetDateTime lastModifiedDate;

}