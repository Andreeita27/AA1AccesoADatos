package com.svalero.RosasTattoo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TattooInDto {
    private long clientId;
    private long professionalId;

    @NotNull(message = "This field is mandatory")
    private LocalDate tattooDate;

    @NotBlank(message = "Style is mandatory")
    private String style;

    @NotBlank(message = "Description is mandatory")
    private String tattooDescription;

    @NotBlank(message = "An image must be uploaded")
    private String imageUrl;

    private int sessions;
    private boolean coverUp;
    private boolean color;
}