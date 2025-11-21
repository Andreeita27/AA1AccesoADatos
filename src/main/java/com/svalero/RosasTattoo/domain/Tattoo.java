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
@Entity(name = "tattoos")
public class Tattoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tattoo_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate tattooDate;

    @Column
    @NotNull
    private String style;

    @Column(name = "tattoo_description")
    private String tattooDescription;

    @Column(name = "image_url")
    @NotNull
    private String imageUrl;

    @Column
    private int sessions;

    @Column(name = "cover_up")
    private boolean coverUp;

    @Column
    private boolean color;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull(message = "Client is mandatory")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    @NotNull(message = "Professional is mandatory")
    private Professional professional;
}