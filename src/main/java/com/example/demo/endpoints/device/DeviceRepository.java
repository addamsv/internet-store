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
    @Query("SELECT d FROM Device d WHERE d.id = ?1") // AND d.deviceInfo.id
    Optional<Device> findByIdPlusDeviceInfo(Long id);

    @Query("SELECT d FROM Device d ORDER BY d.id ASC LIMIT ?1 OFFSET ?2")
    List<Device> findAndCountAll(int limit, int offset);

    @Query("SELECT d FROM Device d WHERE d.brand.id = ?1 ORDER BY d.id ASC LIMIT ?2 OFFSET ?3")
    List<Device> findAndCountByBrand(Long brandId, int limit, int offset);

    @Query("SELECT d FROM Device d WHERE d.type.id = ?1 ORDER BY d.id ASC LIMIT ?2 OFFSET ?3")
    List<Device> findAndCountByType(Long typeId, int limit, int offset);

    @Query("SELECT d FROM Device d WHERE d.brand.id = ?1 AND d.type.id = ?2 ORDER BY d.id ASC LIMIT ?3 OFFSET ?4")
    List<Device> findAndCountByBrandAndType(Long brandId, Long typeId, int limit, int offset);
}
