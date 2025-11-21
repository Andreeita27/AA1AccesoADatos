package com.svalero.RosasTattoo.controller;

import com.svalero.RosasTattoo.domain.Appointment;
import com.svalero.RosasTattoo.dto.AppointmentInDto;
import com.svalero.RosasTattoo.dto.AppointmentOutDto;
import com.svalero.RosasTattoo.exception.ClientNotFoundException;
import com.svalero.RosasTattoo.exception.ErrorResponse;
import com.svalero.RosasTattoo.exception.ProfessionalNotFoundException;
import com.svalero.RosasTattoo.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentOutDto>> getAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody AppointmentInDto appointmentInDto)
            throws ClientNotFoundException, ProfessionalNotFoundException {

        Appointment newAppointment = appointmentService.add(appointmentInDto);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<AppointmentOutDto> getAppointment(@PathVariable long id) throws AppointmentNotFoundException {
        AppointmentOutDto appointment = appointmentService.findById(id);
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity<Appointment> modifyAppointment(@PathVariable long id, @RequestBody AppointmentInDto appointmentInDto)
            throws AppointmentNotFoundException, ClientNotFoundException, ProfessionalNotFoundException {

        Appointment modifiedAppointment = appointmentService.modify(id, appointmentInDto);
        return ResponseEntity.ok(modifiedAppointment);
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable long id) throws AppointmentNotFoundException {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(AppointmentNotFoundException anfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound(anfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}