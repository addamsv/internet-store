package com.example.demo.endpoints.basket;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name= "Basket")
@RequestMapping("api/v1/basket")
@CrossOrigin("*")
public class BasketController {}
