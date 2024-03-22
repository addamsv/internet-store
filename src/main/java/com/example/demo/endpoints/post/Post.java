package com.example.demo.endpoints.post;//interface AttrsToCreatePost {

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
  @Id
  @SequenceGenerator(
    name = "posts_sequence",
    sequenceName = "posts_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "posts_sequence"
  )
  @Schema(example = "1", description = "Unique Post ID")
  Long id;

  @Schema(example = "The Awesome Title", description = "Title of the Post")
  @Column(name = "title", nullable = false)
  String title;

  @Schema(example = "Loren Ip Sum", description = "Posts Content")
  @Column(nullable = false)
  String content;

  @Schema(example = "image.jpg", description = "Image File Name")
  @Column(name = "image")
  String image;

  public Post() {
  }

  public Post(String title, String content, String image) {
    this.title = title;
    this.content = content;
    this.image = image;
  }

  public Post(Long id, String title, String content, String image) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.image = image;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "Post{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", image='" + image + '\'' +
            '}';
  }

//  @ForeignKey(() => User)
//  @Column({ type: DataType.INTEGER })
//  Long userId;
//
//  @BelongsTo(() => User)
//  author: User;
}
