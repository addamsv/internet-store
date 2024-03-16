package com.example.demo.endpoints.device.dto;

import lombok.Data;

@Data
public class GetAllDeviceDTO {
  private Long brandId;

  private Long typeId;

  private Integer limit;

  private Integer page;

  public GetAllDeviceDTO() {
  }

  public GetAllDeviceDTO(Long brandId, Long typeId, Integer limit, Integer page) {
    this.brandId = brandId;
    this.typeId = typeId;
    this.limit = limit;
    this.page = page;
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

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  @Override
  public String toString() {
    return "GetAllDeviceDTO{" +
            "brandId=" + brandId +
            ", typeId=" + typeId +
            ", limit=" + limit +
            ", page=" + page +
            '}';
  }
}
