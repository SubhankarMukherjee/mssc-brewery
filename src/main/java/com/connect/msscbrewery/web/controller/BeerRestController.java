package com.connect.msscbrewery.web.controller;

import com.connect.msscbrewery.web.model.BeerDto;
import com.connect.msscbrewery.web.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Deprecated
@RestController
@RequestMapping("/api/v1/beer")
public class BeerRestController {

    @Autowired
 BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId)
    {
    return new ResponseEntity<>(beerService.getBeerById(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> handlePost(@Valid @RequestBody BeerDto beerDto)
    {
        System.out.println("BeerDTO Name"+beerDto.getBeerName());
        BeerDto dto = beerService.addBeer(beerDto);
        HttpHeaders headers= new HttpHeaders();
        // add hostname to URL
        headers.add("Location","/app/v1/beer/"+ dto.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, @Valid @RequestBody  BeerDto beerDto)
    {
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeerById(@PathVariable String beerId)
    {
        beerService.deleteById(beerId);
    }
}
