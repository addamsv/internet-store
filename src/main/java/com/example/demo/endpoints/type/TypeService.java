package com.example.demo.endpoints.type;

import com.example.demo.endpoints.DTO.RespDTO;
import com.example.demo.endpoints.brand.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class TypeService {
  private final TypeRepository typeRepository;

  @Autowired
  public TypeService(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  public ResponseEntity<RespDTO<List<Type>>> getAll() {
    return new ResponseEntity<>(
            new RespDTO<>("All Types", this.typeRepository.findAll()),
            HttpStatusCode.valueOf(200)
    );
  }

  public ResponseEntity<RespDTO<Type>> create(Type dto) {
    Optional<Type> candidate = this.typeRepository.findTypeByName(dto.getName());

    if (candidate.isPresent()) {
      return new ResponseEntity<>(
              new RespDTO<>("Type is already exist", null),
              HttpStatusCode.valueOf(409));
    }

    return new ResponseEntity<>(
            new RespDTO<>("All Brands", this.typeRepository.save(dto)),
            HttpStatusCode.valueOf(201)//HttpStatus.CREATED
    );
  }

  public ResponseEntity<String> remove(Long id) {
    boolean isCandidateExist = typeRepository.existsById(id);

    if (! isCandidateExist) {
      return new ResponseEntity<>("Type does not exist", HttpStatusCode.valueOf(404));
    }

    typeRepository.deleteById(id);
    return new ResponseEntity<>("Removed", HttpStatusCode.valueOf(200));
  }

  @Transactional
  public ResponseEntity<String> update(Type dto) {
    Type entity = typeRepository.findById(dto.getId()).orElse(null);

    if (entity == null) {
      return new ResponseEntity<>("Type does not exist", HttpStatusCode.valueOf(404));
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
}
