package com.svalero.RosasTattoo.service;

import com.svalero.RosasTattoo.domain.Appointment;
import com.svalero.RosasTattoo.domain.Review;
import com.svalero.RosasTattoo.dto.ReviewInDto;
import com.svalero.RosasTattoo.dto.ReviewOutDto;
import com.svalero.RosasTattoo.exception.AppointmentNotFoundException;
import com.svalero.RosasTattoo.exception.ReviewNotFoundException;
import com.svalero.RosasTattoo.repository.AppointmentRepository;
import com.svalero.RosasTattoo.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AppointmentRepository appointmentRepository; // Necesario para buscar la cita
    @Autowired
    private ModelMapper modelMapper;

    public List<ReviewOutDto> findAll(Integer rating, Long professionalId, Boolean wouldRecommend) {
        List<Review> reviews = reviewRepository.findByFilters(rating, professionalId, wouldRecommend);
        return modelMapper.map(reviews, new TypeToken<List<ReviewOutDto>>() {}.getType());
    }

    public ReviewOutDto findById(long id) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
        return modelMapper.map(review, ReviewOutDto.class);
    }

    public Review add(ReviewInDto reviewInDto) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository.findById(reviewInDto.getAppointmentId())
                .orElseThrow(AppointmentNotFoundException::new);

        Review review = new Review();
        modelMapper.map(reviewInDto, review);

        review.setAppointment(appointment);
        review.setCreatedAt(LocalDateTime.now());
        review.setId(0);

        return reviewRepository.save(review);
    }

    public Review modify(long id, ReviewInDto reviewInDto) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);

        review.setRating(reviewInDto.getRating());
        review.setComment(reviewInDto.getComment());
        review.setWouldRecommend(reviewInDto.isWouldRecommend());

        return reviewRepository.save(review);
    }

    public void delete(long id) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
        reviewRepository.delete(review);
    }
}