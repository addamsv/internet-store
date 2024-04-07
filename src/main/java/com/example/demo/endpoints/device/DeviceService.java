package com.example.demo.endpoints.device;

import com.example.demo.endpoints.DTO.RespRows;
import com.example.demo.endpoints.device.dto.CreateDeviceDTO;
import com.example.demo.endpoints.device.dto.DeviceInfoDTO;
import com.example.demo.files.FilesService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.List;

@Service
public class DeviceService {
  private final DeviceRepository deviceRepository;
  private final FilesService fileService;

  @Autowired
  public DeviceService(
    DeviceRepository deviceRepository,
//    DeviceInfoRepository deviceInfoRepository,
    FilesService fileService
  ) {
    this.deviceRepository = deviceRepository;
    this.fileService = fileService;
  }

  public ResponseEntity<Device> create(CreateDeviceDTO dto) throws IOException {
    System.out.println(dto);

    String fileName = "";

    if (dto.getImg() != null && !dto.getImg().isEmpty()) {
      fileName = "images/" + this.fileService.createFile(dto.getImg());
    }

    Device deviceDTO = new Device(dto.getName(), dto.getPrice(), dto.getRating(), fileName);

    Device device = deviceRepository.save(deviceDTO);

    if (dto.getDeviceInfo() != null && ! dto.getDeviceInfo().isEmpty()) {
      ObjectMapper mapper = new ObjectMapper();
      List<DeviceInfoDTO> deviceInfoArr = mapper.readValue(dto.getDeviceInfo(), new TypeReference<>() {});

     for (DeviceInfoDTO deviceInfo : deviceInfoArr) {
       System.out.println(deviceInfo.getDevice_id());
       System.out.println(deviceInfo.getTitle());
       System.out.println(deviceInfo.getDescription());
//         this.deviceInfoRepository.create(deviceInfo.getTitle(), deviceInfo.getDescription(), device.getId());
     }

    }

    return ResponseEntity.ok(device);
  }

  public Device getById(Long id) {
    return deviceRepository.findByIdPlusDeviceInfo(id)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Device with ID " + id + " not found"));
  }

  public ResponseEntity<RespRows<List<Device>>> getAll(
      Long typeId, Long brandId, Integer limit, Integer page
  ) {
      int offset = (page * limit) - limit;
      System.out.println("offset: " + offset);
      System.out.println("typeId: " + typeId);
      System.out.println("brandId: " + brandId);
      System.out.println("limit: " + limit);
      System.out.println("page: " + page);

    Long count = deviceRepository.count();

    System.out.println("count: " + count);

      /* WHERE BRAND */
    if (brandId > 0 && typeId == 0) {
      return new ResponseEntity<>(
        new RespRows<>(
          "SUCCESS",
          count,
          deviceRepository.findAndCountByBrand(brandId, limit, offset)
        ),
        HttpStatus.OK
      );
    }

    /* WHERE TYPE */
    if (brandId == 0 && typeId > 0) {
      return new ResponseEntity<>(
        new RespRows<>(
          "SUCCESS",
          count,
          deviceRepository.findAndCountByType(typeId, limit, offset)
        ),
        HttpStatus.OK
      );
    }

    if (brandId > 0 && typeId > 0) {
      return new ResponseEntity<>(
        new RespRows<>(
          "SUCCESS",
          count,
          deviceRepository.findAndCountByBrandAndType(brandId, typeId, limit, offset) // .findAll();
        ),
        HttpStatus.OK
      );
    }

    return new ResponseEntity<>(
      new RespRows<>(
        "SUCCESS",
        count,
        deviceRepository.findAndCountAll(limit, offset) // .findAll();
      ),
      HttpStatus.OK
    );
  }

  public ResponseEntity<String> update(Device dto) {
    return ResponseEntity.ok("UPDATE Method has not implemented.");
  }

  public ResponseEntity<String> dell(Long id) {
    return ResponseEntity.ok("DELETE Method has not implemented.");
  }
}
