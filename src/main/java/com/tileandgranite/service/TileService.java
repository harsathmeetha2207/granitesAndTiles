package com.tileandgranite.service;

import com.tileandgranite.dto.TileDTO;
import com.tileandgranite.entity.Tile;
import com.tileandgranite.repository.TileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TileService {
    
    @Autowired
    private TileRepository tileRepository;
    
    public TileDTO createTile(TileDTO tileDTO) {
        Tile tile = Tile.builder()
                .tileCode(tileDTO.getTileCode())
                .name(tileDTO.getName())
                .description(tileDTO.getDescription())
                .size(tileDTO.getSize())
                .color(tileDTO.getColor())
                .material(tileDTO.getMaterial())
                .price(tileDTO.getPrice())
                .stock(tileDTO.getStock())
                .finishType(tileDTO.getFinishType())
                .build();
        
        Tile savedTile = tileRepository.save(tile);
        return convertToDTO(savedTile);
    }
    
    public TileDTO getTileById(Long id) {
        Optional<Tile> tile = tileRepository.findById(id);
        return tile.map(this::convertToDTO).orElse(null);
    }
    
    public TileDTO getTileByCode(String tileCode) {
        Optional<Tile> tile = tileRepository.findByTileCode(tileCode);
        return tile.map(this::convertToDTO).orElse(null);
    }
    
    public List<TileDTO> getAllTiles() {
        return tileRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TileDTO> searchTilesByName(String name) {
        return tileRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TileDTO> getTilesByColor(String color) {
        return tileRepository.findByColor(color).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TileDTO> getTilesByMaterial(String material) {
        return tileRepository.findByMaterial(material).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public TileDTO updateTile(Long id, TileDTO tileDTO) {
        Optional<Tile> existingTile = tileRepository.findById(id);
        
        if (existingTile.isPresent()) {
            Tile tile = existingTile.get();
            tile.setTileCode(tileDTO.getTileCode());
            tile.setName(tileDTO.getName());
            tile.setDescription(tileDTO.getDescription());
            tile.setSize(tileDTO.getSize());
            tile.setColor(tileDTO.getColor());
            tile.setMaterial(tileDTO.getMaterial());
            tile.setPrice(tileDTO.getPrice());
            tile.setStock(tileDTO.getStock());
            tile.setFinishType(tileDTO.getFinishType());
            
            Tile updatedTile = tileRepository.save(tile);
            return convertToDTO(updatedTile);
        }
        return null;
    }
    
    public boolean deleteTile(Long id) {
        if (tileRepository.existsById(id)) {
            tileRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private TileDTO convertToDTO(Tile tile) {
        return TileDTO.builder()
                .id(tile.getId())
                .tileCode(tile.getTileCode())
                .name(tile.getName())
                .description(tile.getDescription())
                .size(tile.getSize())
                .color(tile.getColor())
                .material(tile.getMaterial())
                .price(tile.getPrice())
                .stock(tile.getStock())
                .finishType(tile.getFinishType())
                .build();
    }
}
