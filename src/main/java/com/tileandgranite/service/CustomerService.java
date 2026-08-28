package com.tileandgranite.service;

import com.tileandgranite.dto.CustomerDTO;
import com.tileandgranite.entity.Customer;
import com.tileandgranite.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getPhone())
                .address(customerDTO.getAddress())
                .city(customerDTO.getCity())
                .state(customerDTO.getState())
                .postalCode(customerDTO.getPostalCode())
                .build();
        
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }
    
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(this::convertToDTO).orElse(null);
    }
    
    public CustomerDTO getCustomerByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.map(this::convertToDTO).orElse(null);
    }
    
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CustomerDTO> searchCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstNameContainingIgnoreCase(firstName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CustomerDTO> searchCustomersByLastName(String lastName) {
        return customerRepository.findByLastNameContainingIgnoreCase(lastName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CustomerDTO> getCustomersByCity(String city) {
        return customerRepository.findByCity(city).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setEmail(customerDTO.getEmail());
            customer.setPhone(customerDTO.getPhone());
            customer.setAddress(customerDTO.getAddress());
            customer.setCity(customerDTO.getCity());
            customer.setState(customerDTO.getState());
            customer.setPostalCode(customerDTO.getPostalCode());
            
            Customer updatedCustomer = customerRepository.save(customer);
            return convertToDTO(updatedCustomer);
        }
        return null;
    }
    
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private CustomerDTO convertToDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .city(customer.getCity())
                .state(customer.getState())
                .postalCode(customer.getPostalCode())
                .build();
    }
}
