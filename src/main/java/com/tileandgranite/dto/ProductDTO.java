package com.tileandgranite.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String productCode;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String type;
}
