package com.example.demo.endpoints.post.dto;//interface AttrsToCreatePost {

import com.example.demo.endpoints.post.Post;
import org.springframework.web.multipart.MultipartFile;


public class UpdatePostDTO {
  public Long id;

  public Long userId;

  public String title;

  public String content;

  public MultipartFile image;

  public UpdatePostDTO() {
  }

  public UpdatePostDTO(Long id, Long userId, String title, String content, MultipartFile image) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.content = content;
    this.image = image;
  }

  public Post convert(UpdatePostDTO post, String image) {
    return new Post(id, title, content, image);
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
    return "UpdatePostDTO{" +
            "id=" + id +
            ", userId=" + userId +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", image=" + image +
            '}';
  }
}
