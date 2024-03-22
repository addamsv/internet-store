package com.example.demo.endpoints.post.dto;

import org.springframework.web.multipart.MultipartFile;

public class CreatePostDto {
  public Long userId;

  public String title;

  public String content;

  public MultipartFile image;

  public CreatePostDto() {
  }

  public CreatePostDto(Long userId, String title, String content, MultipartFile image) {
    this.userId = userId;
    this.title = title;
    this.content = content;
    this.image = image;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "CreatePostDto{" +
            "title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", userId=" + userId +
            ", image=" + image +
            '}';
  }
}
