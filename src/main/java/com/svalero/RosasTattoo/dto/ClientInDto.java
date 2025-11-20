package com.svalero.RosasTattoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInDto {
    private String clientName;
    private String clientSurname;
    private String email;
    private String phone;
    private String birthDate;
    private boolean showPhoto;
}
