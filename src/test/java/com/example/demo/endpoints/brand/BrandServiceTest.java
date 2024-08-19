package com.example.demo.endpoints.brand;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    BrandRepository brandRepository;

    @InjectMocks
    BrandService brandService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getByName() {
        Brand brand = new Brand();
        brand.setName("Mi");
        brand.setId(1L);

        // Given
        given(brandRepository.findBrandByName("Mi")).willReturn(Optional.of(brand));

        // When
        ResponseEntity<Brand> brandCandidate = brandService.getByName("Mi");

        // Then
         assertThat(Objects.requireNonNull(brandCandidate.getBody()).getName()).isEqualTo(brand.getName());
         assertThat(brandCandidate.getBody().getId()).isEqualTo(brand.getId());

         verify(brandRepository, times(1)).findBrandByName("Mi");
    }

    @Test
    void getByNameBadRequest() {
        Brand brand = new Brand();
        brand.setName("Mi");
        brand.setId(1L);

        // Given
        given(brandRepository.findBrandByName(Mockito.any(String.class))).willReturn(Optional.empty());

        // When HttpStatus.BAD_REQUEST
        ResponseEntity<Brand> brandCandidate = brandService.getByName("Mi");

        // Then
         assertThat(brandCandidate.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

         verify(brandRepository, times(1)).findBrandByName("Mi");
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
