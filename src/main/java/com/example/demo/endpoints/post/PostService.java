package com.example.demo.endpoints.post;

import com.example.demo.endpoints.post.dto.CreatePostDto;
import com.example.demo.endpoints.post.dto.UpdatePostDTO;
import com.example.demo.files.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

  public ResponseEntity<Post> getById(Long id) {
    Post post = this.postRepository.findById(id).orElse(null);

    if (post == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  public ResponseEntity<List<Post>> getAll() {
    return new ResponseEntity<>(this.postRepository.findAll(), HttpStatus.OK);
  }

  public ResponseEntity<Post> create(CreatePostDto dto) throws IOException {
    String imageFileName = this.fileService.createFile(dto.image);

    // should add an userId
    Post postToSave = new Post(dto.title, dto.content, imageFileName);

    Post post = this.postRepository.save(postToSave);

    return ResponseEntity.ok(post);
  }

  @Transactional
  public ResponseEntity<String> update(UpdatePostDTO dto) throws IOException {
    Post post = dto.convert(dto, null);

    Post entity = postRepository.findById(post.getId()).orElse(null);

    if (entity == null) {
      return new ResponseEntity<>("BAD_REQUEST Entity does not exist", HttpStatus.BAD_REQUEST);
    }

    boolean isUpdated = false;

    /* Title */
    if (
        dto.getTitle() != null && !dto.getTitle().isEmpty()
        && !Objects.equals(entity.getTitle(), dto.getTitle())
    ) {
      entity.setTitle(dto.getTitle());

      isUpdated = true;
    }

    /* Content */
    if (
        dto.getContent() != null && !dto.getContent().isEmpty()
        && !Objects.equals(entity.getContent(), dto.getContent())
    ) {
      entity.setContent(dto.getContent());

      isUpdated = true;
    }

    /* Image */
    if (dto.getImage() != null) {

      String fileName = "";

      if (dto.getImage() != null && ! dto.getImage().isEmpty()) {
        this.fileService.removeFile(entity.getImage());

        fileName = this.fileService.createFile(dto.getImage());
      }

      entity.setImage(fileName);

      isUpdated = true;
    }

    if (isUpdated) {
      return new ResponseEntity<>("Successfully Updated", HttpStatusCode.valueOf(200));
    }

    return new ResponseEntity<>("There is nothing to update", HttpStatusCode.valueOf(400));
  }

  @Transactional
  public ResponseEntity<String> delete(Long id) throws IOException {
    Post entity = postRepository.findById(id).orElse(null);

    if (entity == null) {
      return new ResponseEntity<>("BAD_REQUEST Entity does not exist", HttpStatus.BAD_REQUEST);
    }

    if (entity.getImage() != null && ! entity.getImage().isEmpty()) {
        this.fileService.removeFile(entity.getImage());
    }

    postRepository.deleteById(id);

    return new ResponseEntity<>("Removed", HttpStatus.OK);
  }
}
