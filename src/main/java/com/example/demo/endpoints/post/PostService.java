package com.example.demo.endpoints.post;

import com.example.demo.endpoints.DTO.RespDTO;
import com.example.demo.endpoints.post.dto.CreatePostDto;
import com.example.demo.files.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
  private final PostRepository postRepository;
  private final FilesService fileService;

  @Autowired
  public PostService(
    PostRepository postRepository,
    FilesService fileService
  ) {
    this.fileService = fileService;
    this.postRepository = postRepository;
  }

  public ResponseEntity<RespDTO<List<Post>>> getAll() {
    List<Post> list = new ArrayList<>();
    list.add(new Post());
    return ResponseEntity.ok(new RespDTO<>("SUCCESS", list));
  }

  public ResponseEntity<Post> create(CreatePostDto dto) throws IOException {
    String imageFileName = this.fileService.createFile(dto.image);

    Post postToSave = new Post(dto.title, dto.content, imageFileName);

    Post post = this.postRepository.save(postToSave);

    return ResponseEntity.ok(post);
  }

  public Post update(CreatePostDto dto) {
    throw new Error("Method not implemented.");
  }

  public ResponseEntity<String> dell() {
    return ResponseEntity.ok("Method not implemented.");
  }
}
