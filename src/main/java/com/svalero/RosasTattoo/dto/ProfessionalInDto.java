package com.svalero.RosasTattoo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalInDto {

    @NotBlank(message = "Name is mandatory")
    private String professionalName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String description;

    private String profilePhoto;
    private boolean booksOpened;
    private int yearsExperience;
}