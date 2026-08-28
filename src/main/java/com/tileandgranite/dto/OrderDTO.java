package com.tileandgranite.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private String orderNumber;
    private Long customerId;
    private Double totalAmount;
    private String status;
    private String notes;
}
