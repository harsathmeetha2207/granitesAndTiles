package com.tileandgranite.repository;

import com.tileandgranite.entity.Granite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GraniteRepository extends JpaRepository<Granite, Long> {
    Optional<Granite> findByGraniteCode(String graniteCode);
    java.util.List<Granite> findByNameContainingIgnoreCase(String name);
    java.util.List<Granite> findByColor(String color);
    java.util.List<Granite> findByOrigin(String origin);
}
