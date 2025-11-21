package com.svalero.RosasTattoo.repository;

import com.svalero.RosasTattoo.domain.Appointment;
import com.svalero.RosasTattoo.domain.enums.AppointmentState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    List<Appointment> findAll();

    List<Appointment> findByStateAndClient_IdAndProfessional_Id(AppointmentState state, long clientId, long professionalId);
}
