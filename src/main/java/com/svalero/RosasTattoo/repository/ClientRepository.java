package com.svalero.RosasTattoo.repository;

import com.svalero.RosasTattoo.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findAll();

    List<Client> findByEmail(String email);

    List<Client> findByClientNameContainingAndClientSurnameContainingAndShowPhoto(String clientName, String clientSurname, boolean showPhoto);
}