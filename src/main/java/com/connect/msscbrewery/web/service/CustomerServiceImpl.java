package com.connect.msscbrewery.web.service;

import com.connect.msscbrewery.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    @Override
    public CustomerDTO getCustomerById(UUID custId) {
        return CustomerDTO.builder().id(UUID.randomUUID()).name("John").build();
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDTO customerDTO) {
        log.debug("Customer updated");
    }

    @Override
    public void deleteById(String customerId) {
        log.debug("Customer deleted with"+ customerId);
    }
}
