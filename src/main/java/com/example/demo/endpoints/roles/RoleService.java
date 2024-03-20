package com.example.demo.endpoints.roles;

import com.example.demo.endpoints.DTO.RespDTO;
import com.example.demo.endpoints.brand.Brand;
import com.example.demo.endpoints.device.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

  public ResponseEntity<RespDTO<List<Role>>> getAll() {
    return new ResponseEntity<>(
            new RespDTO<>("SUCCESS", this.roleRepository.findAll()),
            HttpStatusCode.valueOf(200)
    );
  }

  public ResponseEntity<RespDTO<Role>> getRoleByValue(String value) {
    Role candidate = roleRepository.findRoleByValue(value).orElse(null);

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

  public ResponseEntity<RespDTO<Role>> create(Role dto) {
    Optional<Role> candidate = this.roleRepository.findRoleByValue(dto.getValue());

    if (candidate.isPresent()) {
      return new ResponseEntity<>(
              new RespDTO<>("Entity is already exist", null),
              HttpStatusCode.valueOf(409));
    }

    return new ResponseEntity<>(
            new RespDTO<>("Created", this.roleRepository.save(dto)),
            HttpStatusCode.valueOf(201) // HttpStatus.CREATED
    );
  }

  public ResponseEntity<String> update(Role dto) {
    Role entity = roleRepository.findById(dto.getId()).orElse(null);

    if (entity == null) {
      return new ResponseEntity<>("Brand does not exist", HttpStatusCode.valueOf(404));
    }

    /* Value */
    if (
        dto.getValue() != null && !dto.getValue().isEmpty()
        && !Objects.equals(entity.getValue(), dto.getValue())
    ) {
      entity.setValue(dto.getValue());
      return new ResponseEntity<>("Successfully Updated", HttpStatusCode.valueOf(200));
    }

    /* Description */
    if (
        dto.getDescription() != null && !dto.getDescription().isEmpty()
        && !Objects.equals(entity.getDescription(), dto.getDescription())
    ) {
      entity.setDescription(dto.getDescription());
      return new ResponseEntity<>("Successfully Updated", HttpStatusCode.valueOf(200));
    }

    return new ResponseEntity<>("There is nothing to update", HttpStatusCode.valueOf(400));
  }

  public ResponseEntity<String> delete(Long id) {
    boolean isCandidateExist = roleRepository.existsById(id);

    if (! isCandidateExist) {
      return new ResponseEntity<>("Entity does not exist", HttpStatusCode.valueOf(404));
    }

    roleRepository.deleteById(id);

    return new ResponseEntity<>("Removed", HttpStatusCode.valueOf(200));
  }
}
