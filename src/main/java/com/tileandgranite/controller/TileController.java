package com.tileandgranite.controller;

import com.tileandgranite.dto.TileDTO;
import com.tileandgranite.service.TileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tiles")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TileController {
    
    @Autowired
    private TileService tileService;
    
    @PostMapping
    public ResponseEntity<?> createTile(@RequestBody TileDTO tileDTO) {
        try {
            TileDTO createdTile = tileService.createTile(tileDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating tile: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getTileById(@PathVariable Long id) {
        TileDTO tile = tileService.getTileById(id);
        if (tile != null) {
            return ResponseEntity.ok(tile);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tile not found");
    }
    
    @GetMapping("/code/{tileCode}")
    public ResponseEntity<?> getTileByCode(@PathVariable String tileCode) {
        TileDTO tile = tileService.getTileByCode(tileCode);
        if (tile != null) {
            return ResponseEntity.ok(tile);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tile not found");
    }
    
    @GetMapping
    public ResponseEntity<List<TileDTO>> getAllTiles() {
        List<TileDTO> tiles = tileService.getAllTiles();
        return ResponseEntity.ok(tiles);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<TileDTO>> searchTiles(@RequestParam String name) {
        List<TileDTO> tiles = tileService.searchTilesByName(name);
        return ResponseEntity.ok(tiles);
    }
    
    @GetMapping("/color/{color}")
    public ResponseEntity<List<TileDTO>> getTilesByColor(@PathVariable String color) {
        List<TileDTO> tiles = tileService.getTilesByColor(color);
        return ResponseEntity.ok(tiles);
    }
    
    @GetMapping("/material/{material}")
    public ResponseEntity<List<TileDTO>> getTilesByMaterial(@PathVariable String material) {
        List<TileDTO> tiles = tileService.getTilesByMaterial(material);
        return ResponseEntity.ok(tiles);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTile(@PathVariable Long id, @RequestBody TileDTO tileDTO) {
        try {
            TileDTO updatedTile = tileService.updateTile(id, tileDTO);
            if (updatedTile != null) {
                return ResponseEntity.ok(updatedTile);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tile not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating tile: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTile(@PathVariable Long id) {
        boolean deleted = tileService.deleteTile(id);
        if (deleted) {
            return ResponseEntity.ok("Tile deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tile not found");
    }
}
