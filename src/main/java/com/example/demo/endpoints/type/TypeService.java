package com.example.demo.endpoints.type;

import com.example.demo.endpoints.brand.Brand;
import org.springframework.beans.factory.annotation.Autowired;
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

  public List<Type> getAll() {
    return this.typeRepository.findAll();//{ include: { all: true } }
  }

  public Type create(Type type) {
    Optional<Type> probe = this.typeRepository.findTypeByName(type.getName());

    if (probe.isPresent()) {
      throw new IllegalStateException("Name is already exist");
    }
    return this.typeRepository.save(type);
  }

  public void remove(Long id) {
    boolean isCandidateExist = typeRepository.existsById(id);

    if (!isCandidateExist) {
      throw new IllegalStateException("Type does not exist");
    }

    typeRepository.deleteById(id);
  }

  @Transactional
  public void update(Type dto) {
    Type type = typeRepository.findById(dto.getId())
            .orElseThrow(() -> new IllegalStateException("Type does not exist"));

    if (
      dto.getName() != null
      && !dto.getName().isEmpty()
      && !Objects.equals(type.getName(), dto.getName())
    ) {
      type.setName(dto.getName());
    }
  }
}
