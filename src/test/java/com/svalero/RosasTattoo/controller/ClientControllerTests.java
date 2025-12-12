package com.svalero.RosasTattoo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svalero.RosasTattoo.dto.ClientOutDto;
import com.svalero.RosasTattoo.service.ClientService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    @MockitoBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAll() throws Exception {
        List<ClientOutDto> clientsOutDto = List.of(
                new ClientOutDto("Andrea", "Fernandez", "andrea@example.com", "601375656", LocalDate.of(1998, 1, 26), true, 3),
                new ClientOutDto("Pepe", "Garcia", "pepe@example.com", "600999888", LocalDate.of(1990, 5, 20), false, 0)
        );

        when(clientService.findAll("", "", null)).thenReturn(clientsOutDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/clients")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<ClientOutDto> clientsListResponse = objectMapper.readValue(jsonResponse, new TypeReference<>(){});

        assertNotNull(clientsListResponse);
        assertEquals(2, clientsListResponse.size());
        assertEquals("Andrea", clientsListResponse.getFirst().getClientName());
    }

    @Test
    public void testGetById() throws Exception {
        ClientOutDto clientOutDto = new ClientOutDto("Andrea", "Fernandez", "andrea@example.com", "601375656", LocalDate.of(1998, 1, 26), true, 3);

        when(clientService.findById(1L)).thenReturn(clientOutDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/clients/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        ClientOutDto clientResponse = objectMapper.readValue(jsonResponse, ClientOutDto.class);

        assertNotNull(clientResponse);
        assertEquals("Andrea", clientResponse.getClientName());
    }
}