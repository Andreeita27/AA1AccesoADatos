package com.svalero.RosasTattoo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "professional")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "professional_name")
    @NotNull(message = "Name is mandatory")
    private String professionalName;

    @Column(name = "birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column
    private String description;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "books_opened")
    private boolean booksOpened;

    @Column(name = "years_experience")
    private int yearsExperience;
}