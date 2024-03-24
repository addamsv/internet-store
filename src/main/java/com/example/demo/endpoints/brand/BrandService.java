package com.example.demo.endpoints.brand;

import com.example.demo.endpoints.DTO.RespDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

//  public ResponseEntity<RespDTO<List<Brand>>> getAll() {
  public ResponseEntity<List<Brand>> getAll() {
    return new ResponseEntity<>(
//            new RespDTO<>("SUCCESS", this.brandRepository.findAll()),
            this.brandRepository.findAll(),
            HttpStatusCode.valueOf(200)
    );
  }

  public ResponseEntity<RespDTO<Brand>> getByName(String name) {
    Brand candidate = this.brandRepository.findBrandByName(name).orElse(null);

    if (candidate == null) {
      return new ResponseEntity<>(
              new RespDTO<>("Entity does not exist", null),
              HttpStatusCode.valueOf(409));
    }

    return new ResponseEntity<>(
            new RespDTO<>("SUCCESS", candidate),
            HttpStatusCode.valueOf(200)
    );
  }

  public ResponseEntity<RespDTO<Brand>> create(Brand dto) {
    Optional<Brand> candidate = this.brandRepository.findBrandByName(dto.getName());

    if (candidate.isPresent()) {
      return new ResponseEntity<>(
              new RespDTO<>("Entity is already exist", null),
              HttpStatusCode.valueOf(409));
    }

    return new ResponseEntity<>(
            new RespDTO<>("Created", this.brandRepository.save(dto)),
            HttpStatusCode.valueOf(201)//HttpStatus.CREATED
    );
  }

  @Transactional
  public ResponseEntity<String> update(Brand dto) {
    Brand entity = brandRepository.findById(dto.getId()).orElse(null);

    if (entity == null) {
      return new ResponseEntity<>("Entity does not exist", HttpStatusCode.valueOf(404));
    }

    if (
        dto.getName() != null && !dto.getName().isEmpty()
        && !Objects.equals(entity.getName(), dto.getName())
    ) {
      entity.setName(dto.getName());
      return new ResponseEntity<>("Successfully Updated", HttpStatusCode.valueOf(200));
    }

    return new ResponseEntity<>("There is nothing to update", HttpStatusCode.valueOf(400));
  }

  public ResponseEntity<String> delete(Long id) {
    boolean isCandidateExist = brandRepository.existsById(id);

    if (! isCandidateExist) {
      return new ResponseEntity<>("Entity does not exist", HttpStatusCode.valueOf(404));
    }

    brandRepository.deleteById(id);

    return new ResponseEntity<>("Removed", HttpStatusCode.valueOf(200));
  }
}
