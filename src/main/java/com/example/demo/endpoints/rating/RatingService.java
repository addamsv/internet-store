package com.example.demo.endpoints.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public ResponseEntity<List<Rating>> getAll() {
        return new ResponseEntity<>(this.ratingRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Rating> create(Rating dto) {
        Optional<Rating> candidate = this.ratingRepository.findById(dto.getId());

        if (candidate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.ratingRepository.save(dto), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<String> update(Rating dto) {
        Rating entity = ratingRepository.findById(dto.getId()).orElse(null);

        if (entity == null) {
            return new ResponseEntity<>("Entity does not exist", HttpStatus.NOT_FOUND);
        }

        if (
            !Objects.equals(entity.getRate(), dto.getRate())
        ) {
            entity.setRate(dto.getRate());
            return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
        }

        return new ResponseEntity<>("There is nothing to update", HttpStatusCode.valueOf(400));

    }

    public ResponseEntity<String> delete(Long id) {
        boolean isCandidateExist = ratingRepository.existsById(id);

        if (! isCandidateExist) {
            return new ResponseEntity<>("Entity does not exist", HttpStatus.NOT_FOUND);
        }

        ratingRepository.deleteById(id);

        return new ResponseEntity<>("SUCCESSFULLY Removed", HttpStatus.OK);
    }
}
