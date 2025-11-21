package com.svalero.RosasTattoo.controller;

import com.svalero.RosasTattoo.domain.Professional;
import com.svalero.RosasTattoo.dto.ProfessionalInDto;
import com.svalero.RosasTattoo.dto.ProfessionalOutDto;
import com.svalero.RosasTattoo.exception.ErrorResponse;
import com.svalero.RosasTattoo.exception.ProfessionalNotFoundException;
import com.svalero.RosasTattoo.service.ProfessionalService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/professionals")
    public ResponseEntity<List<ProfessionalOutDto>> getAll(
            @RequestParam(value = "professionalName", required = false) String professionalName,
            @RequestParam(value = "booksOpened", required = false) Boolean booksOpened,
            @RequestParam(value = "yearsExperience", required = false) Integer yearsExperience
    ) {
        List<ProfessionalOutDto> professionals = professionalService.findAll(professionalName, booksOpened, yearsExperience);
        return ResponseEntity.ok(professionals);
    }

    @GetMapping("/professionals/{id}")
    public ResponseEntity<ProfessionalOutDto> get(@PathVariable long id) throws ProfessionalNotFoundException {
        ProfessionalOutDto professional = professionalService.findById(id);
        return ResponseEntity.ok(professional);
    }

    @PostMapping("/professionals")
    public ResponseEntity<Professional> addProfessional(@Valid @RequestBody ProfessionalInDto professionalInDto) {
        Professional professional = modelMapper.map(professionalInDto, Professional.class);
        Professional newProfessional = professionalService.add(professional);
        return new ResponseEntity<>(newProfessional, HttpStatus.CREATED);
    }

    @PutMapping("/professionals/{id}")
    public ResponseEntity<Professional> modifyProfessional(@PathVariable long id, @RequestBody ProfessionalInDto professionalInDto) throws ProfessionalNotFoundException {
        Professional professional = modelMapper.map(professionalInDto, Professional.class);
        Professional modifiedProfessional = professionalService.modify(id, professional);
        return ResponseEntity.ok(modifiedProfessional);
    }

    @DeleteMapping("/professionals/{id}")
    public ResponseEntity<Void> deleteProfessional(@PathVariable long id) throws ProfessionalNotFoundException {
        professionalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ProfessionalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ProfessionalNotFoundException pnfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound(pnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorResponse errorResponse = ErrorResponse.validationError(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}