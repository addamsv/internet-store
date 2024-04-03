package com.example.demo.endpoints.device.dto;

public class DeviceInfoDTO {
    private Long id;
    private Long device_id;
    private String title;
    private String description;

    public DeviceInfoDTO(Long device_id, String title, String description) {
        this.title = title;
        this.description = description;
        this.device_id = device_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeviceInfoDTO{" +
                "device_id=" + device_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
