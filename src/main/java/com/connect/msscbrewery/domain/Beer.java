package com.connect.msscbrewery.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Beer {

        private UUID id;

        private String beerName;

        private String beerStyle;

        private Long upc;

        }
