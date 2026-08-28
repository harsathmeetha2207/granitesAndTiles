package com.tileandgranite.controller;

import com.tileandgranite.dto.ProductDTO;
import com.tileandgranite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO createdProduct = productService.createProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating product: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
    
    @GetMapping("/code/{productCode}")
    public ResponseEntity<?> getProductByCode(@PathVariable String productCode) {
        ProductDTO product = productService.getProductByCode(productCode);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
    
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String name) {
        List<ProductDTO> products = productService.searchProductsByName(name);
        return ResponseEntity.ok(products);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
            if (updatedProduct != null) {
                return ResponseEntity.ok(updatedProduct);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating product: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
}
