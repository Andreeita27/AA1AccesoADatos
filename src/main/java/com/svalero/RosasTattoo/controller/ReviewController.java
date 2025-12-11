package com.svalero.RosasTattoo.controller;

import com.svalero.RosasTattoo.domain.Review;
import com.svalero.RosasTattoo.dto.ReviewInDto;
import com.svalero.RosasTattoo.dto.ReviewOutDto;
import com.svalero.RosasTattoo.exception.AppointmentNotFoundException;
import com.svalero.RosasTattoo.exception.ErrorResponse;
import com.svalero.RosasTattoo.exception.ReviewNotFoundException;
import com.svalero.RosasTattoo.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewOutDto>> getAll(
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Long professionalId,
            @RequestParam(required = false) Boolean wouldRecommend) {
        return ResponseEntity.ok(reviewService.findAll(rating, professionalId, wouldRecommend));
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewOutDto> getReview(@PathVariable long id) throws ReviewNotFoundException {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@Valid @RequestBody ReviewInDto reviewInDto)
            throws AppointmentNotFoundException {
        return new ResponseEntity<>(reviewService.add(reviewInDto), HttpStatus.CREATED);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> modifyReview(@PathVariable long id, @RequestBody ReviewInDto reviewInDto)
            throws ReviewNotFoundException {
        return ResponseEntity.ok(reviewService.modify(id, reviewInDto));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) throws ReviewNotFoundException {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleReviewException(ReviewNotFoundException rnfe) {
        return new ResponseEntity<>(ErrorResponse.notFound(rnfe.getMessage()), HttpStatus.NOT_FOUND);
    }
}