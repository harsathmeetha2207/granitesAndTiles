package com.tileandgranite.controller;

import com.tileandgranite.dto.GraniteDTO;
import com.tileandgranite.service.GraniteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/granites")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GraniteController {
    
    @Autowired
    private GraniteService graniteService;
    
    @PostMapping
    public ResponseEntity<?> createGranite(@RequestBody GraniteDTO graniteDTO) {
        try {
            GraniteDTO createdGranite = graniteService.createGranite(graniteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGranite);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating granite: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getGraniteById(@PathVariable Long id) {
        GraniteDTO granite = graniteService.getGraniteById(id);
        if (granite != null) {
            return ResponseEntity.ok(granite);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Granite not found");
    }
    
    @GetMapping("/code/{graniteCode}")
    public ResponseEntity<?> getGraniteByCode(@PathVariable String graniteCode) {
        GraniteDTO granite = graniteService.getGraniteByCode(graniteCode);
        if (granite != null) {
            return ResponseEntity.ok(granite);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Granite not found");
    }
    
    @GetMapping
    public ResponseEntity<List<GraniteDTO>> getAllGranites() {
        List<GraniteDTO> granites = graniteService.getAllGranites();
        return ResponseEntity.ok(granites);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<GraniteDTO>> searchGranites(@RequestParam String name) {
        List<GraniteDTO> granites = graniteService.searchGranitesByName(name);
        return ResponseEntity.ok(granites);
    }
    
    @GetMapping("/color/{color}")
    public ResponseEntity<List<GraniteDTO>> getGranitesByColor(@PathVariable String color) {
        List<GraniteDTO> granites = graniteService.getGranitesByColor(color);
        return ResponseEntity.ok(granites);
    }
    
    @GetMapping("/origin/{origin}")
    public ResponseEntity<List<GraniteDTO>> getGranitesByOrigin(@PathVariable String origin) {
        List<GraniteDTO> granites = graniteService.getGranitesByOrigin(origin);
        return ResponseEntity.ok(granites);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGranite(@PathVariable Long id, @RequestBody GraniteDTO graniteDTO) {
        try {
            GraniteDTO updatedGranite = graniteService.updateGranite(id, graniteDTO);
            if (updatedGranite != null) {
                return ResponseEntity.ok(updatedGranite);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Granite not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating granite: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGranite(@PathVariable Long id) {
        boolean deleted = graniteService.deleteGranite(id);
        if (deleted) {
            return ResponseEntity.ok("Granite deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Granite not found");
    }
}
