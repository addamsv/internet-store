package com.example.demo.endpoints.rating;

import com.example.demo.endpoints.DTO.RespDTO;
import com.example.demo.endpoints.brand.Brand;
import com.example.demo.endpoints.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResponseEntity<RespDTO<List<Rating>>> getAll() {
        return new ResponseEntity<>(
                new RespDTO<>("SUCCESS", this.ratingRepository.findAll()),
                HttpStatusCode.valueOf(200)
        );
    }

    public ResponseEntity<RespDTO<Rating>> create(Rating dto) {
        Optional<Rating> candidate = this.ratingRepository.findById(dto.getId());

        if (candidate.isPresent()) {
            return new ResponseEntity<>(
                    new RespDTO<>("Entity is already exist", null),
                    HttpStatusCode.valueOf(409));
        }

        return new ResponseEntity<>(
                new RespDTO<>("Created", this.ratingRepository.save(dto)),
                HttpStatusCode.valueOf(201)//HttpStatus.CREATED
        );
    }

    @Transactional
    public ResponseEntity<String> update(Rating dto) {
        Rating entity = ratingRepository.findById(dto.getId()).orElse(null);

        if (entity == null) {
            return new ResponseEntity<>("Entity does not exist", HttpStatusCode.valueOf(404));
        }

        if (
            !Objects.equals(entity.getRate(), dto.getRate())
        ) {
            entity.setRate(dto.getRate());
            return new ResponseEntity<>("Successfully Updated", HttpStatusCode.valueOf(200));
        }

        return new ResponseEntity<>("There is nothing to update", HttpStatusCode.valueOf(400));

    }

    public ResponseEntity<String> delete(Long id) {
        boolean isCandidateExist = ratingRepository.existsById(id);

        if (! isCandidateExist) {
            return new ResponseEntity<>("Entity does not exist", HttpStatusCode.valueOf(404));
        }

        ratingRepository.deleteById(id);

        return new ResponseEntity<>("SUCCESSFULLY Removed", HttpStatusCode.valueOf(200));
    }
}
