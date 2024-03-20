package com.example.demo.endpoints.device.dto;


import org.springframework.web.multipart.MultipartFile;

public class CreateDeviceDTO {
  private String name;

  private Integer price;

  private Long brandId;

  private Long typeId;

  private Integer rating;

  private MultipartFile img;

  private String deviceInfo;


  public CreateDeviceDTO() {}

  public CreateDeviceDTO(
          String name, Integer price, Long brandId, Long typeId, MultipartFile img, String deviceInfo,
          Integer rating
  ) {
    this.name = name;
    this.price = price;
    this.brandId = brandId;
    this.typeId = typeId;
    this.img = img;
    this.deviceInfo = deviceInfo;
    this.rating = rating;
  }



  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Long getBrandId() {
    return brandId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public MultipartFile getImg() {
    return img;
  }

  public void setImg(MultipartFile img) {
    this.img = img;
  }

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  @Override
  public String toString() {
    return "CreateDeviceDTO{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", brandId=" + brandId +
            ", typeId=" + typeId +
            ", img='" + img.getOriginalFilename() + '\'' +
            ", deviceInfo='" + deviceInfo + '\'' +
            '}';
  }
}
