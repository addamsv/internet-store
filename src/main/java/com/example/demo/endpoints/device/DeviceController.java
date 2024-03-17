package com.example.demo.endpoints.device;

import com.example.demo.endpoints.device.dto.CreateDeviceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Tag(name = "Device")
@RestController
@RequestMapping("api/v1/device")
@CrossOrigin("*")
public class DeviceController {
  DeviceService deviceService;

  @Autowired
  public DeviceController(DeviceService deviceService) {
    this.deviceService = deviceService;
  }

  @GetMapping(params = {"typeId", "brandId", "page", "limit"})
  @Operation(
          description = "Get All",
          summary = "Getting all devices",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          useReturnTypeSchema = true
                  )
          }
  )
  public List<Device> getAll(
          @RequestParam(required = true, name = "typeId", defaultValue = "0") Long typeId,
          @RequestParam(required = true, name = "brandId", defaultValue = "0") Long brandId,
          @RequestParam(required = true, name = "limit", defaultValue = "0") Integer limit,
          @RequestParam(required = true, name = "page", defaultValue = "0") Integer page
//          @RequestParam Map<String, String> reqParam
  ) {
    return this.deviceService.getAll(typeId, brandId, limit, page);
  }

  @GetMapping(path = "/{id}")
  @Operation(
          description = "Get by id",
          summary = "Get device by ID",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          useReturnTypeSchema = true
                  )
          }
  )
  public Device getById(@PathVariable("id") Long id) {
    return this.deviceService.getById(id);
  }

  @PostMapping()// (params = "img") @UseInterceptors(FileInterceptor('img'))
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
          description = "POST Create",
          summary = "Create Device",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          useReturnTypeSchema = true
                  )
          }
  )
  public ResponseEntity<Device> create(
          @ModelAttribute CreateDeviceDTO dto) throws URISyntaxException, IOException {
//          @RequestBody()
//          @RequestParam(required = true, name = "img") MultipartFile img // @UploadedFile() Image img
//  ) throws IOException {
    return this.deviceService.create(dto);
  }

  @PutMapping()
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
          description = "PUT Update Device",
          summary = "Update Device",
          responses = {
               @ApiResponse(
                       description = "Success",
                       responseCode = "200",
                       useReturnTypeSchema = true
               )
          }
  )
  public Device update(@RequestBody() Device dto) {
    return this.deviceService.update(dto);
  }

  @DeleteMapping()
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
          description = "DELETE",
          summary = "Remove device",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          useReturnTypeSchema = true
                  )
          }
  )
  public void delete() {
    this.deviceService.dell();
  }
}
