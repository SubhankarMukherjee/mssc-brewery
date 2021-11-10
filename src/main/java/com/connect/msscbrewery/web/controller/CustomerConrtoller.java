package com.connect.msscbrewery.web.controller;

import com.connect.msscbrewery.web.model.CustomerDTO;
import com.connect.msscbrewery.web.model.CustomerDTO;
import com.connect.msscbrewery.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class CustomerConrtoller {

    @RestController
    @RequestMapping("/api/v1/customer")
    public class BeerRestController {

        @Autowired
        private CustomerService customerService;

        @GetMapping("/{customerId}")
        public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerId") UUID custId) {
            return new ResponseEntity<>(customerService.getCustomerById(custId), HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<CustomerDTO> handlePost(@RequestBody CustomerDTO customerDTO) {
            System.out.println("Customer DTO Name" + customerDTO.getName());
            CustomerDTO customerDTO1 = customerService.addCustomer(customerDTO);
            HttpHeaders headers = new HttpHeaders();
            // add hostname to URL
            headers.add("Location", "/app/v1/customer" + customerDTO1.getId().toString());
            return new ResponseEntity(headers, HttpStatus.CREATED);
        }

        @PutMapping("/{customerId}")
        public ResponseEntity handleUpdate(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customerDTO) {
            customerService.updateCustomer(customerId, customerDTO);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/{customerId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteBeerById(@PathVariable String customerId) {
            customerService.deleteById(customerId);
        }


    }

}