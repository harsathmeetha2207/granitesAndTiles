package com.tileandgranite.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TileDTO {
    private Long id;
    private String tileCode;
    private String name;
    private String description;
    private String size;
    private String color;
    private String material;
    private Double price;
    private Integer stock;
    private String finishType;
}
