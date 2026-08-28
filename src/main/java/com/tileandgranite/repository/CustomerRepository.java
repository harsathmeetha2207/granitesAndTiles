package com.tileandgranite.repository;

import com.tileandgranite.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    java.util.List<Customer> findByFirstNameContainingIgnoreCase(String firstName);
    java.util.List<Customer> findByLastNameContainingIgnoreCase(String lastName);
    java.util.List<Customer> findByCity(String city);
}
