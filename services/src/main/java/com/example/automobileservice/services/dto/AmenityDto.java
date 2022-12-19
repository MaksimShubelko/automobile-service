package com.example.automobileservice.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class AmenityDto {

    private Long id;

    @Pattern(regexp = "^[\sa-zA-z]{5,20}$", message = "Should contain a-z, A-Z, " +
            "or space and be not shorter than 5 chars and no longer, than 20 chars")
    private String description;
}
