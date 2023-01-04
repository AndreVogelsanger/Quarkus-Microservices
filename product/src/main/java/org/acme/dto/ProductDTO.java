package org.acme.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private String category;
    private String model;
    private BigDecimal price;


}