package com.example.demo.endpoints.brand;

import org.springframework.beans.factory.annotation.Autowired;
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

  public List<Brand> getAll() {
    return this.brandRepository.findAll();
  }

  public Brand create(Brand brand) {
    Optional<Brand> probe = this.brandRepository.findBrandByName(brand.getName());

    if (probe.isPresent()) {
      throw new IllegalStateException("Name is already exist");
    }

    return this.brandRepository.save(brand);
  }

  public void remove(Long id) {
    boolean isCandidateExist = brandRepository.existsById(id);

    if (!isCandidateExist) {
      throw new IllegalStateException("Brand does not exist");
    }

    brandRepository.deleteById(id);
  }

  @Transactional
  public void update(Brand dto) {
    Brand brand = brandRepository.findById(dto.getId())
            .orElseThrow(() -> new IllegalStateException("Brand does not exist"));

    if (
      dto.getName() != null
      && !dto.getName().isEmpty()
      && !Objects.equals(brand.getName(), dto.getName())
    ) {
      brand.setName(dto.getName());
    }
  }

  public Brand getBrandByName(String name) {
    return null;
  }
}
