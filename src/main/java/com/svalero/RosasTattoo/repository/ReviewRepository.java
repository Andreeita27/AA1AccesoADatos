package com.svalero.RosasTattoo.repository;

import com.svalero.RosasTattoo.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findAll();

    @Query("SELECT r FROM reviews r WHERE " +
            "(:rating IS NULL OR r.rating = :rating) AND " +
            "(:profId IS NULL OR r.appointment.professional.id = :profId) AND " +
            "(:recommend IS NULL OR r.wouldRecommend = :recommend)")
    List<Review> findByFilters(
            @Param("rating") Integer rating,
            @Param("profId") Long profId,
            @Param("recommend") Boolean recommend
    );
}