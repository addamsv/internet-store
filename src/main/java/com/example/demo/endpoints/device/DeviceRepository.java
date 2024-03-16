package com.example.demo.endpoints.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    // SELECT * FROM "device" WHERE "name" = ?;
    @Query("SELECT d FROM Device d WHERE d.name = ?1")
    Optional<Device> findDeviceByName(String name);

    // SELECT * FROM "device" WHERE "id" = ?; //INCLUDE [{ model: DeviceInfo, as: 'deviceInfo' }]
    @Query("SELECT d FROM Device d WHERE d.id = ?1")
    Optional<Device> findByIdPlusDeviceInfo(Long id);

    @Query("SELECT d FROM Device d")
    Optional<List<Device>> findAndCountAll(Integer limit, Integer offset);
}
