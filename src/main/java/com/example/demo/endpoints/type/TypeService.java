package com.example.demo.endpoints.type;

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
public class TypeService {
  private final TypeRepository typeRepository;

  @Autowired
  public TypeService(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  public ResponseEntity<List<Type>> getAll() {
    return new ResponseEntity<>(
        this.typeRepository.findAll(),
        HttpStatus.OK
    );
  }

  public ResponseEntity<Type> create(Type dto) {
    Optional<Type> candidate = this.typeRepository.findTypeByName(dto.getName());

    if (candidate.isPresent()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(this.typeRepository.save(dto), HttpStatus.CREATED);
  }

  public ResponseEntity<String> remove(Long id) {
    boolean isCandidateExist = typeRepository.existsById(id);

    if (! isCandidateExist) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    typeRepository.deleteById(id);

    return new ResponseEntity<>("Removed", HttpStatus.OK);
  }

  @Transactional
  public ResponseEntity<String> update(Type dto) {
    Type entity = typeRepository.findById(dto.getId()).orElse(null);

    if (entity == null) {
      return new ResponseEntity<>("Type does not exist", HttpStatus.BAD_REQUEST);
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
}
