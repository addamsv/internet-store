package com.example.demo.endpoints.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public ResponseEntity<List<Address>> get() {
        return new ResponseEntity<>(this.addressRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Address> create(Address dto) {
        Optional<Address> candidate = this.addressRepository
                .findByAddress(
                        dto.getZipCode(),
                        dto.getStreet(),
                        dto.getHouseNumber()
                );

        if (candidate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.addressRepository.save(dto), HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<String> update(Address dto) {
        Address entity = addressRepository.findById(dto.getId()).orElse(null);

        if (entity == null) {
            return new ResponseEntity<>("Entity does not exist", HttpStatus.NOT_FOUND);
        }

        boolean isUpdated = false;

        /* street */
        if (
            dto.getStreet() != null && !dto.getStreet().isEmpty()
            && !Objects.equals(entity.getStreet(), dto.getStreet())
        ) {
            entity.setStreet(dto.getStreet());
        }

        /* houseNumber */
        if (
            dto.getHouseNumber() != null && ! dto.getHouseNumber().isEmpty()
            && ! Objects.equals(entity.getHouseNumber(), dto.getHouseNumber())
        ) {
            entity.setHouseNumber(dto.getHouseNumber());
            isUpdated = true;
        }

        /* zipCode */
        if (
            dto.getZipCode() != null && ! dto.getZipCode().isEmpty()
            && ! Objects.equals(entity.getZipCode(), dto.getZipCode())
        ) {
            System.out.println("========== UPDATED ============");
            entity.setZipCode(dto.getZipCode());
            isUpdated = true;
        }

        if (isUpdated) {
            /* should be validation same address (address like this is already exist) */
            return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
        }

        return new ResponseEntity<>("There is nothing to update", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> delete(Long id) {
        boolean isCandidateExist = addressRepository.existsById(id);

        if (! isCandidateExist) {
            return new ResponseEntity<>("Entity does not exist", HttpStatus.NOT_FOUND);
        }

        addressRepository.deleteById(id);

        return new ResponseEntity<>("Removed", HttpStatus.OK);
    }
}
