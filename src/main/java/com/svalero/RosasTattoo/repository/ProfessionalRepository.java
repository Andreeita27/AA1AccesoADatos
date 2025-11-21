package com.svalero.RosasTattoo.repository;

import com.svalero.RosasTattoo.domain.Professional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalRepository extends CrudRepository<Professional, Long> {

    List<Professional> findAll();

    List<Professional> findByProfessionalName(String professionalName);

    List<Professional> findByProfessionalNameContainingAndBooksOpenedAndYearsExperience(String professionalName, boolean booksOpened, int yearsExperience);
}
