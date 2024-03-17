package com.example.demo.files;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FilesService {
  public String createFile(MultipartFile file) throws IOException {

    String fileName = UUID.randomUUID() + ".jpg";

    String fileDir = "src/main/resources/static/images";

    Path uploadPath = Path.of(fileDir);

    Path filePath = uploadPath.resolve(fileName);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

    return fileName;
  }
}
