package com.tileandgranite.service;

import com.tileandgranite.dto.GraniteDTO;
import com.tileandgranite.entity.Granite;
import com.tileandgranite.repository.GraniteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GraniteService {
    
    @Autowired
    private GraniteRepository graniteRepository;
    
    public GraniteDTO createGranite(GraniteDTO graniteDTO) {
        Granite granite = Granite.builder()
                .graniteCode(graniteDTO.getGraniteCode())
                .name(graniteDTO.getName())
                .description(graniteDTO.getDescription())
                .origin(graniteDTO.getOrigin())
                .color(graniteDTO.getColor())
                .pattern(graniteDTO.getPattern())
                .price(graniteDTO.getPrice())
                .quantity(graniteDTO.getQuantity())
                .finishType(graniteDTO.getFinishType())
                .thickness(graniteDTO.getThickness())
                .build();
        
        Granite savedGranite = graniteRepository.save(granite);
        return convertToDTO(savedGranite);
    }
    
    public GraniteDTO getGraniteById(Long id) {
        Optional<Granite> granite = graniteRepository.findById(id);
        return granite.map(this::convertToDTO).orElse(null);
    }
    
    public GraniteDTO getGraniteByCode(String graniteCode) {
        Optional<Granite> granite = graniteRepository.findByGraniteCode(graniteCode);
        return granite.map(this::convertToDTO).orElse(null);
    }
    
    public List<GraniteDTO> getAllGranites() {
        return graniteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<GraniteDTO> searchGranitesByName(String name) {
        return graniteRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<GraniteDTO> getGranitesByColor(String color) {
        return graniteRepository.findByColor(color).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<GraniteDTO> getGranitesByOrigin(String origin) {
        return graniteRepository.findByOrigin(origin).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public GraniteDTO updateGranite(Long id, GraniteDTO graniteDTO) {
        Optional<Granite> existingGranite = graniteRepository.findById(id);
        
        if (existingGranite.isPresent()) {
            Granite granite = existingGranite.get();
            granite.setGraniteCode(graniteDTO.getGraniteCode());
            granite.setName(graniteDTO.getName());
            granite.setDescription(graniteDTO.getDescription());
            granite.setOrigin(graniteDTO.getOrigin());
            granite.setColor(graniteDTO.getColor());
            granite.setPattern(graniteDTO.getPattern());
            granite.setPrice(graniteDTO.getPrice());
            granite.setQuantity(graniteDTO.getQuantity());
            granite.setFinishType(graniteDTO.getFinishType());
            granite.setThickness(graniteDTO.getThickness());
            
            Granite updatedGranite = graniteRepository.save(granite);
            return convertToDTO(updatedGranite);
        }
        return null;
    }
    
    public boolean deleteGranite(Long id) {
        if (graniteRepository.existsById(id)) {
            graniteRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private GraniteDTO convertToDTO(Granite granite) {
        return GraniteDTO.builder()
                .id(granite.getId())
                .graniteCode(granite.getGraniteCode())
                .name(granite.getName())
                .description(granite.getDescription())
                .origin(granite.getOrigin())
                .color(granite.getColor())
                .pattern(granite.getPattern())
                .price(granite.getPrice())
                .quantity(granite.getQuantity())
                .finishType(granite.getFinishType())
                .thickness(granite.getThickness())
                .build();
    }
}
