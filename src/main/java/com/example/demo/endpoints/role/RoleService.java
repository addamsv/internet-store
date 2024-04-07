package com.example.demo.endpoints.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoleService {
  private final RoleRepository roleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public ResponseEntity<List<Role>> getAll() {
    return new ResponseEntity<>(this.roleRepository.findAll(), HttpStatus.OK);
  }

  public ResponseEntity<Role> getRoleByValue(String value) {
    Role candidate = roleRepository.findRoleByValue(value).orElse(null);

    if (candidate == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(candidate, HttpStatus.OK);
  }

  public ResponseEntity<Role> create(Role dto) {
    Optional<Role> candidate = this.roleRepository.findRoleByValue(dto.getValue());

    if (candidate.isPresent()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(this.roleRepository.save(dto), HttpStatus.CREATED);
  }

  @Transactional
  public ResponseEntity<String> update(Role dto) {
    Role entity = roleRepository.findById(dto.getId()).orElse(null);

    if (entity == null) {
      return new ResponseEntity<>("Entity does not exist", HttpStatus.NOT_FOUND);
    }

    /* Value */
    if (
        dto.getValue() != null && !dto.getValue().isEmpty()
        && !Objects.equals(entity.getValue(), dto.getValue())
    ) {
      entity.setValue(dto.getValue());
      return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
    }

    /* Description */
    if (
        dto.getDescription() != null && !dto.getDescription().isEmpty()
        && !Objects.equals(entity.getDescription(), dto.getDescription())
    ) {
      entity.setDescription(dto.getDescription());
      return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
    }

    return new ResponseEntity<>("There is nothing to update", HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<String> delete(Long id) {
    boolean isCandidateExist = roleRepository.existsById(id);

    if (! isCandidateExist) {
      return new ResponseEntity<>("Entity does not exist", HttpStatus.NOT_FOUND);
    }

    roleRepository.deleteById(id);

    return new ResponseEntity<>("Removed", HttpStatus.OK);
  }

  public ResponseEntity<List<Role>> getRoleByUserId(Long userId) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}
