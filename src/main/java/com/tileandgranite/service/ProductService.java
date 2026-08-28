package com.tileandgranite.service;

import com.tileandgranite.dto.ProductDTO;
import com.tileandgranite.entity.Product;
import com.tileandgranite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .productCode(productDTO.getProductCode())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .type(productDTO.getType())
                .build();
        
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDTO).orElse(null);
    }
    
    public ProductDTO getProductByCode(String productCode) {
        Optional<Product> product = productRepository.findByProductCode(productCode);
        return product.map(this::convertToDTO).orElse(null);
    }
    
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<ProductDTO> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepository.findById(id);
        
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setProductCode(productDTO.getProductCode());
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setQuantity(productDTO.getQuantity());
            product.setType(productDTO.getType());
            
            Product updatedProduct = productRepository.save(product);
            return convertToDTO(updatedProduct);
        }
        return null;
    }
    
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .productCode(product.getProductCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .type(product.getType())
                .build();
    }
}
