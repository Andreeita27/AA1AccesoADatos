package com.svalero.RosasTattoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TattooDto {
    private long id;
    private long clientId;
    private long professionalId;
    private String professionalName;
    private LocalDate tattooDate;
    private String style;
    private String tattooDescription;
    private String imageUrl;
    private int sessions;
    private boolean coverUp;
    private boolean color;
}