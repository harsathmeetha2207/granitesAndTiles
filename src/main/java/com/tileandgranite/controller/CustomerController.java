package com.tileandgranite.controller;

import com.tileandgranite.dto.CustomerDTO;
import com.tileandgranite.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating customer: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String email) {
        CustomerDTO customer = customerService.getCustomerByEmail(email);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }
    
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/search/firstName")
    public ResponseEntity<List<CustomerDTO>> searchByFirstName(@RequestParam String firstName) {
        List<CustomerDTO> customers = customerService.searchCustomersByFirstName(firstName);
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/search/lastName")
    public ResponseEntity<List<CustomerDTO>> searchByLastName(@RequestParam String lastName) {
        List<CustomerDTO> customers = customerService.searchCustomersByLastName(lastName);
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/city/{city}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByCity(@PathVariable String city) {
        List<CustomerDTO> customers = customerService.getCustomersByCity(city);
        return ResponseEntity.ok(customers);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
            if (updatedCustomer != null) {
                return ResponseEntity.ok(updatedCustomer);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating customer: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return ResponseEntity.ok("Customer deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }
}
