package com.svalero.RosasTattoo.controller;

import com.svalero.RosasTattoo.domain.Client;
import com.svalero.RosasTattoo.dto.ClientInDto;
import com.svalero.RosasTattoo.dto.ClientOutDto;
import com.svalero.RosasTattoo.exception.ClientNotFoundException;
import com.svalero.RosasTattoo.exception.ErrorResponse;
import com.svalero.RosasTattoo.service.ClientService;
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
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientOutDto>> getAll(
            @RequestParam(value = "clientName", defaultValue = "") String clientName,
            @RequestParam(value = "clientSurname", defaultValue = "") String clientSurname,
            @RequestParam(value = "showPhoto", required = false) Boolean showPhoto
    ) {
        List<ClientOutDto> clientsOutDto = clientService.findAll(clientName, clientSurname, showPhoto);
        return ResponseEntity.ok(clientsOutDto);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientOutDto> get(@PathVariable long id) throws ClientNotFoundException {
        ClientOutDto clientOutDto = clientService.findById(id);
        return ResponseEntity.ok(clientOutDto);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@Valid @RequestBody ClientInDto clientInDto) {
        Client client = modelMapper.map(clientInDto, Client.class);
        Client newClient = clientService.add(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> modifyClient(@PathVariable long id, @RequestBody ClientInDto clientInDto) throws ClientNotFoundException {
        Client client = modelMapper.map(clientInDto, Client.class);
        Client newClient = clientService.modify(id, client);
        return ResponseEntity.ok(newClient);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable long id) throws ClientNotFoundException {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ClientNotFoundException cnfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound(cnfe.getMessage());
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