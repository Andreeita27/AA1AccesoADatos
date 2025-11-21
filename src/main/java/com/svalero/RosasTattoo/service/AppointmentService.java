package com.svalero.RosasTattoo.service;

import com.svalero.RosasTattoo.domain.Appointment;
import com.svalero.RosasTattoo.domain.Client;
import com.svalero.RosasTattoo.domain.Professional;
import com.svalero.RosasTattoo.dto.AppointmentInDto;
import com.svalero.RosasTattoo.dto.AppointmentOutDto;
import com.svalero.RosasTattoo.exception.ClientNotFoundException;
import com.svalero.RosasTattoo.exception.ProfessionalNotFoundException;
import com.svalero.RosasTattoo.repository.AppointmentRepository;
import com.svalero.RosasTattoo.repository.ClientRepository;
import com.svalero.RosasTattoo.repository.ProfessionalRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProfessionalRepository professionalRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<AppointmentOutDto> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return modelMapper.map(appointments, new TypeToken<List<AppointmentOutDto>>() {}.getType());
    }

    public Appointment add(AppointmentInDto appointmentInDto) throws ClientNotFoundException, ProfessionalNotFoundException {
        Client client = clientRepository.findById(appointmentInDto.getClientId())
                .orElseThrow(ClientNotFoundException::new);

        List<Professional> professionals = professionalRepository.findByProfessionalName(appointmentInDto.getProfessionalName());
        if (professionals.isEmpty()) {
            throw new ProfessionalNotFoundException();
        }
        Professional professional = professionals.get(0);

        Appointment appointment = new Appointment();
        modelMapper.map(appointmentInDto, appointment);
        appointment.setClient(client);
        appointment.setProfessional(professional);
        appointment.setDepositPaid(false);

        return appointmentRepository.save(appointment);
    }

    public Appointment modify(long id, AppointmentInDto appointmentInDto) throws AppointmentNotFoundException, ClientNotFoundException, ProfessionalNotFoundException {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(AppointmentNotFoundException::new);

        Client client = clientRepository.findById(appointmentInDto.getClientId())
                .orElseThrow(ClientNotFoundException::new);

        List<Professional> professionals = professionalRepository.findByProfessionalName(appointmentInDto.getProfessionalName());
        if (professionals.isEmpty()) {
            throw new ProfessionalNotFoundException();
        }
        Professional professional = professionals.get(0);
        modelMapper.map(appointmentInDto, appointment);
        appointment.setId(id);
        appointment.setClient(client);
        appointment.setProfessional(professional);

        return appointmentRepository.save(appointment);
    }

    public void delete(long id) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(AppointmentNotFoundException::new);

        appointmentRepository.delete(appointment);
    }
}