package com.example.demo.endpoints.roles;

import com.example.demo.endpoints.device.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
  private final RoleRepository roleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public Role create(Role dto) {
    return roleRepository.save(dto);
  }

  public Role getRoleByValue(String value) {
    return roleRepository.findRoleByValue(value)
            .orElseThrow(() -> new Error("Something wrong"));
  }

  public Role update(Role dto) {
    throw new Error("Method not implemented.");
  }

  public ResponseEntity<String> remove(Long id) {
    return ResponseEntity.ok("Removed");
  }
}
