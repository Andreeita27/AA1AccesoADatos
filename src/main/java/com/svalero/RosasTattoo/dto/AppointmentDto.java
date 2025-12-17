package com.svalero.RosasTattoo.dto;

import com.svalero.RosasTattoo.domain.enums.AppointmentState;
import com.svalero.RosasTattoo.domain.enums.TattooSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private long id;
    private LocalDateTime startDateTime;
    private String professionalName;
    private long professionalId;
    private long clientId;
    private String bodyPlacement;
    private String ideaDescription;
    private boolean firstTime;
    private TattooSize tattooSize;
    private String referenceImageUrl;
    private int durationMinutes;
    private float price;
    private AppointmentState state;
    private boolean depositPaid;
}
