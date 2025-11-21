package com.svalero.RosasTattoo.dto;

import com.svalero.RosasTattoo.domain.enums.TattooSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentInDto {
    private long clientId;
    private String professionalName;
    private LocalDateTime startDateTime;
    private String bodyPlacement;
    private String ideaDescription;
    private boolean firstTime;
    private TattooSize tattooSize;
    private String referenceImageUrl;
}