package com.svalero.RosasTattoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewInDto {
    private long appointmentId;
    private int rating;
    private String comment;
    private boolean wouldRecommend;
}