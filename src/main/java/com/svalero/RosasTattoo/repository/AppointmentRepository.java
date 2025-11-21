package com.svalero.RosasTattoo.repository;

import com.svalero.RosasTattoo.domain.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    List<Appointment> findAll();

    // Aquí podrías añadir filtros si quisieras, por ejemplo:
    // List<Appointment> findByState(AppointmentState state);
}
