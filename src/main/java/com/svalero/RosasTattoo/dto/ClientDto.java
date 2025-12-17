package com.svalero.RosasTattoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private long id;
    private String clientName;
    private String clientSurname;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private boolean showPhoto;
    private int visits;
}
