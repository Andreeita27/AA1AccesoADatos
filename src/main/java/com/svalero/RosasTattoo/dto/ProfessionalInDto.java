package com.svalero.RosasTattoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalInDto {
    private String professionalName;
    private LocalDate birthDate;
    private String description;
    private String profilePhoto;
    private boolean booksOpened;
    private int yearsExperience;
}