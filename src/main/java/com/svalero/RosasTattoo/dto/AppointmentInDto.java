package com.svalero.RosasTattoo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @NotNull(message = "Client is mandatory")
    private long clientId;

    @NotNull(message = "Professional is mandatory")
    private long professionalId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "This field is mandatory")
    private LocalDateTime startDateTime;

    @NotNull(message = "This field is mandatory")
    private String bodyPlacement;

    @NotNull(message = "An idea must be described")
    private String ideaDescription;

    private boolean firstTime;

    @Enumerated(EnumType.STRING)
    private TattooSize tattooSize;

    private String referenceImageUrl;
}