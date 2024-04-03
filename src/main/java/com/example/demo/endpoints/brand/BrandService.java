package com.example.demo.endpoints.brand;

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
public class BrandService {
  private final BrandRepository brandRepository;
  @Autowired
  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public ResponseEntity<List<Brand>> getAll() {
    return new ResponseEntity<>(this.brandRepository.findAll(), HttpStatus.OK);
  }

  public ResponseEntity<Brand> getByName(String name) {
    Brand candidate = this.brandRepository.findBrandByName(name).orElse(null);

    if (candidate == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(candidate, HttpStatus.OK);
  }

  public ResponseEntity<Brand> create(Brand dto) {
    Optional<Brand> candidate = this.brandRepository.findBrandByName(dto.getName());

    if (candidate.isPresent()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(this.brandRepository.save(dto), HttpStatus.CREATED);
  }

  @Transactional
  public ResponseEntity<String> update(Brand dto) {
    Brand entity = brandRepository.findById(dto.getId()).orElse(null);

    if (entity == null) {
      return new ResponseEntity<>("Entity does not exist", HttpStatus.NOT_FOUND);
    }

    if (
        dto.getName() != null && !dto.getName().isEmpty()
        && !Objects.equals(entity.getName(), dto.getName())
    ) {
      entity.setName(dto.getName());
      return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
    }

    return new ResponseEntity<>("There is nothing to update", HttpStatusCode.valueOf(400));
  }

  public ResponseEntity<String> delete(Long id) {
    boolean isCandidateExist = brandRepository.existsById(id);

    if (! isCandidateExist) {
      return new ResponseEntity<>("Entity does not exist", HttpStatus.NOT_FOUND);
    }

    brandRepository.deleteById(id);

    return new ResponseEntity<>("Removed", HttpStatus.OK);
  }
}
