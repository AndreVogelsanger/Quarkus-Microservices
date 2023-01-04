package org.acme.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class OrderDTO {

    private Long customerId;
    private String customerName;
    private Long productId;
    private BigDecimal orderValue;

}
