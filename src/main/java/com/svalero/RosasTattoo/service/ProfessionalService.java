package com.svalero.RosasTattoo.service;

import com.svalero.RosasTattoo.domain.Professional;
import com.svalero.RosasTattoo.dto.ProfessionalOutDto;
import com.svalero.RosasTattoo.exception.ProfessionalNotFoundException;
import com.svalero.RosasTattoo.repository.ProfessionalRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ProfessionalOutDto> findAll(String name, Boolean booksOpened, Integer yearsExperience) {
        List<Professional> professionals;

        if ((name != null && !name.isEmpty()) || booksOpened != null || yearsExperience != null) {
            String queryName = name != null ? name : "";
            boolean queryBooks = booksOpened != null ? booksOpened : false;
            int queryYears = yearsExperience != null ? yearsExperience : 0;

            professionals = professionalRepository.findByProfessionalNameContainingAndBooksOpenedAndYearsExperience(queryName, queryBooks, queryYears);
        } else {
            professionals = professionalRepository.findAll();
        }

        return modelMapper.map(professionals, new TypeToken<List<ProfessionalOutDto>>() {}.getType());
    }

    public ProfessionalOutDto findById(long id) throws ProfessionalNotFoundException {
        Professional professional = professionalRepository.findById(id)
                .orElseThrow(ProfessionalNotFoundException::new);

        return modelMapper.map(professional, ProfessionalOutDto.class);
    }

    public Professional add(Professional professional) {
        return professionalRepository.save(professional);
    }

    public Professional modify(long id, Professional professional) throws ProfessionalNotFoundException {
        Professional existingProfessional = professionalRepository.findById(id)
                .orElseThrow(ProfessionalNotFoundException::new);

        modelMapper.map(professional, existingProfessional);
        existingProfessional.setId(id);

        return professionalRepository.save(existingProfessional);
    }

    public void delete(long id) throws ProfessionalNotFoundException {
        Professional professional = professionalRepository.findById(id)
                .orElseThrow(ProfessionalNotFoundException::new);

        professionalRepository.delete(professional);
    }
}