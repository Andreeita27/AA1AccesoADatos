package com.svalero.RosasTattoo.dto;

import com.svalero.RosasTattoo.domain.enums.TattooSize;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentInDto {

    private long clientId;
    private long professionalId;

    @NotNull(message = "This field is mandatory")
    private LocalDateTime startDateTime;

    @NotNull(message = "This field is mandatory")
    private String bodyPlacement;

    @NotNull(message = "You must describe your idea")
    private String ideaDescription;

    private boolean firstTime;

    @Enumerated(EnumType.STRING)
    private TattooSize tattooSize;

    private String referenceImageUrl;
}