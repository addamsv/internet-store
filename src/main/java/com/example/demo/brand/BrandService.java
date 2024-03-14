package com.example.demo.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
  private final BrandRepository brandRepository;
  @Autowired
  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public Brand create(Brand brand) {
    return null;
  }

  public List<Brand> getAllBrands() {
    return null;
  }

  public void removeBrand(Long id) {
  }

  public Brand updateBrand(Brand dto) {
    return null;
  }

  public Brand getBrandByName(String name) {
    return null;
  }
}
