package com.svalero.RosasTattoo.service;

import com.svalero.RosasTattoo.domain.Client;
import com.svalero.RosasTattoo.domain.Professional;
import com.svalero.RosasTattoo.domain.Tattoo;
import com.svalero.RosasTattoo.dto.TattooInDto;
import com.svalero.RosasTattoo.dto.TattooOutDto;
import com.svalero.RosasTattoo.exception.ClientNotFoundException;
import com.svalero.RosasTattoo.exception.ProfessionalNotFoundException;
import com.svalero.RosasTattoo.repository.ClientRepository;
import com.svalero.RosasTattoo.repository.ProfessionalRepository;
import com.svalero.RosasTattoo.repository.TattooRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TattooService {

    @Autowired
    private TattooRepository tattooRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProfessionalRepository professionalRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<TattooOutDto> findAll(String style, Boolean coverUp, Boolean color) {
        List<Tattoo> tattoos;

        if (style != null || coverUp != null || color != null) {
            String queryStyle = style != null ? style : "";
            boolean queryCoverUp = coverUp != null ? coverUp : false;
            boolean queryColor = color != null ? color : false;

            tattoos = tattooRepository.findByStyleContainingAndCoverUpAndColor(queryStyle, queryCoverUp, queryColor);
        } else {
            tattoos = tattooRepository.findAll();
        }
        return modelMapper.map(tattoos, new TypeToken<List<TattooOutDto>>() {}.getType());
    }

    public TattooOutDto findById(long id) throws TattooNotFoundException {
        Tattoo tattoo = tattooRepository.findById(id).orElseThrow(TattooNotFoundException::new);
        return modelMapper.map(tattoo, TattooOutDto.class);
    }

    public Tattoo add(TattooInDto tattooInDto) throws ClientNotFoundException, ProfessionalNotFoundException {
        Client client = clientRepository.findById(tattooInDto.getClientId())
                .orElseThrow(ClientNotFoundException::new);

        List<Professional> professionals = professionalRepository.findByProfessionalName(tattooInDto.getProfessionalName());
        if (professionals.isEmpty()) throw new ProfessionalNotFoundException();
        Professional professional = professionals.get(0);

        Tattoo tattoo = new Tattoo();
        modelMapper.map(tattooInDto, tattoo);
        tattoo.setClient(client);
        tattoo.setProfessional(professional);
        tattoo.setId(0);

        return tattooRepository.save(tattoo);
    }

    public Tattoo modify(long id, TattooInDto tattooInDto) throws TattooNotFoundException, ClientNotFoundException, ProfessionalNotFoundException {
        Tattoo tattoo = tattooRepository.findById(id).orElseThrow(TattooNotFoundException::new);
        Client client = clientRepository.findById(tattooInDto.getClientId()).orElseThrow(ClientNotFoundException::new);

        List<Professional> professionals = professionalRepository.findByProfessionalName(tattooInDto.getProfessionalName());
        if (professionals.isEmpty()) throw new ProfessionalNotFoundException();
        Professional professional = professionals.get(0);

        modelMapper.map(tattooInDto, tattoo);
        tattoo.setId(id);
        tattoo.setClient(client);
        tattoo.setProfessional(professional);

        return tattooRepository.save(tattoo);
    }

    public void delete(long id) throws TattooNotFoundException {
        Tattoo tattoo = tattooRepository.findById(id).orElseThrow(TattooNotFoundException::new);
        tattooRepository.delete(tattoo);
    }
}