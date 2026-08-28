package com.tileandgranite.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GraniteDTO {
    private Long id;
    private String graniteCode;
    private String name;
    private String description;
    private String origin;
    private String color;
    private String pattern;
    private Double price;
    private Integer quantity;
    private String finishType;
    private Double thickness;
}
