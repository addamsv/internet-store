package com.example.demo.endpoints.device;

import com.example.demo.endpoints.device.dto.CreateDeviceDTO;
import com.example.demo.files.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

  public ResponseEntity<Device> create(CreateDeviceDTO dto) throws IOException {//, MultipartFile img
    System.out.println(dto);
//    System.out.println(image);

    String fileName = "";

    if (dto.getImg() != null && !dto.getImg().isEmpty()) {
      fileName = this.fileService.createFile(dto.getImg());
    }

    Device deviceDTO = new Device(dto.getName(), dto.getPrice(), dto.getRating(), fileName);

    Device device = deviceRepository.save(deviceDTO);
//    var device = this.deviceRepository.create({
//      ...dto,
//      img: fileName,
//    });
//
//    if (dto.deviceInfo) {
//      const info = JSON.parse(dto.deviceInfo);
//
//      info.forEach((inf) => {
//        this.deviceInfoRepository.create({
//          title: inf.title,
//          description: inf.description,
//          deviceId: device.id,
//        });
//      });
//    }
//
//    return device;
    return ResponseEntity.ok(device);
  }

  public Device getById(Long id) {
    return deviceRepository.findByIdPlusDeviceInfo(id)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Device with ID " + id + " not found"));
  }

  public List<Device> getAll(
          Long typeId, Long brandId, Integer limit, Integer page
  ) {
      System.out.println(typeId);
      System.out.println(brandId);
      System.out.println(limit);
      System.out.println(page);
      Integer offset = page * limit - limit;
      System.out.println(offset);

      return deviceRepository.findAndCountAll(limit, offset)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "There are not any devices"));
//    if (brandId != null && typeId != null) {
//    }
//
//    if (brandId && !typeId) {
//      List<Device> devices = deviceRepository.findAndCountAll({
//        include: { all: true },
//        where: { brandId },
//        limit,
//        offset,
//      });
//
//      return devices;
//    }
//
//    if (!brandId && typeId) {
//      List<Device> devices = deviceRepository.findAndCountAll({
//        include: { all: true },
//        where: { typeId },
//        limit,
//        offset,
//      });
//
//      return devices;
//    }
//
//    if (brandId && typeId) {
//      List<Device> devices = deviceRepository.findAndCountAll({
//        include: { all: true },
//        where: { brandId, typeId },
//        limit,
//        offset,
//      });
//
//      return devices;
//    }
//    List<Device> list = new ArrayList<>();
//    list.add(new Device(1L, "fake", 1000, 10, ""));
//    return list;
  }

  public void dell() {
    throw new Error("Method not implemented.");
  }

  public Device update(Device dto) {
    throw new Error("Method not implemented.");
  }
}
