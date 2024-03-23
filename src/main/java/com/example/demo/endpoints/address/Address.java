package com.example.demo.endpoints.address;

import com.example.demo.endpoints.users.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @Schema(example = "1", description = "Address ID")
    private Long id;

    @Schema(example = "Pravda", description = "Name of avenue")
    @Column(name = "street", nullable = false)
    private String street;

    @Schema(example = "60", description = "number of a house")
    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Schema(example = "220116", description = "zip code")
    @Column(name = "zip_code", nullable = false)
    private String zipCode;


    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;




    public Address() {
    }

    public Address(String street, String houseNumber, String zipCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
    }

    public Address(Long id, String street, String houseNumber, String zipCode, Users users) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.users = users;
    }


    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
