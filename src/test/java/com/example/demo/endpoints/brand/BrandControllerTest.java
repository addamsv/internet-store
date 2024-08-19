package com.example.demo.endpoints.brand;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BrandService brandService;

    List<ResponseEntity<Brand>> brands;

    @BeforeEach
    void setUp() {
        this.brands = new ArrayList<>();

        ResponseEntity<Brand> mi = new ResponseEntity<>(new Brand("Mi"), HttpStatus.OK);
        this.brands.add(mi);

        ResponseEntity<Brand> iPhone = new ResponseEntity<>(new Brand("iPhone"), HttpStatus.OK);
        this.brands.add(iPhone);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getByName() throws Exception {
        // Given (behavior of the Service
        given(this.brandService.getByName("Mi")).willReturn(this.brands.get(0));
        // When and Then
        /*
        {
            "id": 1,
            "name": "Mi",
            "devices": [],
            "types": []
        }
        */
        this.mockMvc.perform(get("/api/v1/brand?name=Mi").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Mi"));
    }

    @Test
    void getAll() {
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
