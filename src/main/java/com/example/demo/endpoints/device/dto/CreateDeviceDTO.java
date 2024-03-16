package com.example.demo.endpoints.device.dto;

import lombok.Data;

@Data
public class CreateDeviceDTO {
  private String name;

  private Integer price;

  private Long brandId;

  private Long typeId;

  private String img;

  private String deviceInfo;

  public CreateDeviceDTO() {}

  public CreateDeviceDTO(String name, Integer price, Long brandId, Long typeId, String img, String deviceInfo) {
    this.name = name;
    this.price = price;
    this.brandId = brandId;
    this.typeId = typeId;
    this.img = img;
    this.deviceInfo = deviceInfo;
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

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
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
            ", img='" + img + '\'' +
            ", deviceInfo='" + deviceInfo + '\'' +
            '}';
  }
}
