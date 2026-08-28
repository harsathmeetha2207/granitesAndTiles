package com.tileandgranite.repository;

import com.tileandgranite.entity.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TileRepository extends JpaRepository<Tile, Long> {
    Optional<Tile> findByTileCode(String tileCode);
    java.util.List<Tile> findByNameContainingIgnoreCase(String name);
    java.util.List<Tile> findByColor(String color);
    java.util.List<Tile> findByMaterial(String material);
}
