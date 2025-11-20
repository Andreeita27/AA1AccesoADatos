package com.svalero.RosasTattoo.service;

import com.svalero.RosasTattoo.domain.Client;
import com.svalero.RosasTattoo.dto.ClientOutDto;
import com.svalero.RosasTattoo.exception.ClientNotFoundException;
import com.svalero.RosasTattoo.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Client add(Client client) {
        return clientRepository.save(client);
    }

    public void delete(long id) throws ClientNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        clientRepository.delete(client);
    }

    public List<ClientOutDto> findAll(String name, String surname, Boolean showPhoto) {
        List<Client> clients;

        if ((name != null && !name.isEmpty()) || (surname != null && !surname.isEmpty()) || showPhoto != null) {
            String queryName = name != null ? name : "";
            String querySurname = surname != null ? surname : "";
            boolean queryShowPhoto = showPhoto != null ? showPhoto : false;

            clients = clientRepository.findByClientNameContainingAndClientSurnameContainingAndShowPhoto(queryName, querySurname, queryShowPhoto);
        } else {
            clients = clientRepository.findAll();
        }

        return modelMapper.map(clients, new TypeToken<List<ClientOutDto>>() {}.getType());
    }

    public ClientOutDto findById(long id) throws ClientNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        ClientOutDto clientOutDto = modelMapper.map(client, ClientOutDto.class);
        return clientOutDto;
    }
    
    public Client modify(long id, Client client) throws ClientNotFoundException {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        modelMapper.map(client, existingClient);
        existingClient.setId(id);

        return clientRepository.save(existingClient);
    }
}