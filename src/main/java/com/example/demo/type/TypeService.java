package com.example.demo.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypeService {
  private final TypeRepository typeRepository;

  @Autowired
  public TypeService(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  public Type create(Type type) {
    return this.typeRepository.save(type);
  }

  public void removeType(Long typeId) {
    boolean isTypeCandidateExist = typeRepository.existsById(typeId);

    if (!isTypeCandidateExist) {
      throw new IllegalStateException("type does not exist");
    }

    typeRepository.deleteById(typeId);

  }

  public Type updateType(Type typeDTO) {
    return this.typeRepository.save(typeDTO);
  }

  public List<Type> getAll() {
    return this.typeRepository.findAll();//{ include: { all: true } }
  }
}
