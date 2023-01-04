package org.acme.dto;


import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String name;
    private String phone;
    private String email;
    private String address;
    private String age;


}
