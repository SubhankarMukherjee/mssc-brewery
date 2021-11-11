package com.connect.msscbrewery.web.controller.V2;

import com.connect.msscbrewery.web.model.V2.BeerDTOV2;

import com.connect.msscbrewery.web.service.V2.BeerServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v2/beer")
public class BeerRestControllerV2 {

    @Autowired
    private BeerServiceV2 beerServiceV2;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTOV2> getBeer(@NotNull  @PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerServiceV2.getBeerById(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTOV2> handlePost(@Valid @RequestBody BeerDTOV2 beerDTOV2) {
        System.out.println("BeerDTO Name" + beerDTOV2.getBeerName());
        BeerDTOV2 createdbeerDTOV2 = beerServiceV2.addBeer(beerDTOV2);
        HttpHeaders headers = new HttpHeaders();
        // add hostname to URL
        headers.add("Location", "/app/v1/beer" + createdbeerDTOV2.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDTOV2 beerDTOV2) {
        beerServiceV2.updateBeer(beerId, beerDTOV2);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeerById(@PathVariable String beerId) {
        beerServiceV2.deleteById(beerId);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity exceptionHanding(ConstraintViolationException e)
    {
        List<String> errors= new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> errors
                .add(constraintViolation.getPropertyPath()+":"+ constraintViolation.getMessage()));
        return  new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
    }

}
