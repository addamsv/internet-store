package com.example.demo.endpoints.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order")
public class Order {
    @Id
    Long id;
}
