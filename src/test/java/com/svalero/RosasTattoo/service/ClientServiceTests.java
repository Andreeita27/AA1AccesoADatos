package com.svalero.RosasTattoo.service;

import com.svalero.RosasTattoo.domain.Client;
import com.svalero.RosasTattoo.dto.ClientOutDto;
import com.svalero.RosasTattoo.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testFindAll() {
        List<Client> mockClientList = List.of(
                new Client(1L, "Andrea", "Fernandez", "andrea@example.com", "601375656", LocalDate.of(1998, 1, 26), true, 3),
                new Client(2L, "Pepe", "Garcia", "pepe@example.com", "600999888", LocalDate.of(1990, 5, 20), false, 0)
        );

        List<ClientOutDto> modelMapperOut = List.of(
                new ClientOutDto("Andrea", "Fernandez", "andrea@example.com", "601375656", LocalDate.of(1998, 1, 26), true, 3),
                new ClientOutDto("Pepe", "Garcia", "pepe@example.com", "600999888", LocalDate.of(1990, 5, 20), false, 0)
        );

        when(clientRepository.findByFilters(null, null, null)).thenReturn(mockClientList);

        when(modelMapper.map(mockClientList, new TypeToken<List<ClientOutDto>>() {}.getType())).thenReturn(modelMapperOut);

        List<ClientOutDto> actualClientList = clientService.findAll(null, null, null);

        assertEquals(2L, actualClientList.size());
        assertEquals("Andrea", actualClientList.getFirst().getClientName());
        assertEquals("Pepe", actualClientList.getLast().getClientName());

        verify(clientRepository, times(1)).findByFilters(null, null, null);
    }

    @Test
    public void testFindById() throws com.svalero.RosasTattoo.exception.ClientNotFoundException {
        Client mockClient = new Client(1L, "Andrea", "Fernandez", "andrea@example.com", "601375656", LocalDate.of(1998, 1, 26), true, 3);
        ClientOutDto mockOutDto = new ClientOutDto("Andrea", "Fernandez", "andrea@example.com", "600123456", LocalDate.of(1998, 1, 26), true, 3);

        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(mockClient));

        when(modelMapper.map(mockClient, ClientOutDto.class)).thenReturn(mockOutDto);

        ClientOutDto result = clientService.findById(1L);

        assertEquals("Andrea", result.getClientName());

        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    public void testAdd() {
        Client newClient = new Client();
        newClient.setClientName("Andrea");
        newClient.setClientSurname("Fernandez");

        Client savedClient = new Client(1L, "Andrea", "Fernandez", "andrea@example.com", "601375656", LocalDate.of(1998, 1, 26), true, 0);

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        Client result = clientService.add(newClient);

        assertEquals(1L, result.getId());
        assertEquals("Andrea", result.getClientName());

        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testModify() throws com.svalero.RosasTattoo.exception.ClientNotFoundException {
        Client newClientData = new Client();
        newClientData.setClientName("Andrea Modificada");

        Client existingClient = new Client(1L, "Andrea", "Fernandez", "andrea@example.com", "601375656", LocalDate.of(1998, 1, 26), true, 3);

        Client updatedClient = new Client(1L, "Andrea Modificada", "Fernandez", "andrea@example.com", "601375656", LocalDate.of(1998, 1, 26), true, 3);

        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(existingClient));

        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        Client result = clientService.modify(1L, newClientData);

        assertEquals("Andrea Modificada", result.getClientName());

        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testDelete() throws com.svalero.RosasTattoo.exception.ClientNotFoundException {
        Client mockClient = new Client(1L, "Andrea", "Fernandez", "andrea@example.com", "601375656", LocalDate.of(1998, 1, 26), true, 3);

        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(mockClient));

        clientService.delete(1L);

        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).delete(mockClient);
    }
}