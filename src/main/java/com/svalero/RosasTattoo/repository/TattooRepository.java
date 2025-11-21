package com.svalero.RosasTattoo.repository;

import com.svalero.RosasTattoo.domain.Tattoo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TattooRepository extends CrudRepository<Tattoo, Long> {

    List<Tattoo> findAll();

    List<Tattoo> findByStyleContainingAndCoverUpAndColor(String style, boolean coverUp, boolean color);
}