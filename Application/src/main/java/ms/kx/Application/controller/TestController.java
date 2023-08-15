package ms.kx.Application.controller;

import lombok.AllArgsConstructor;
import ms.kx.Application.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class TestController {
    private final ImageService imageService;

    @RequestMapping("/api/save")
    public void saveImageToDb(@RequestBody MultipartFile image) throws IOException {
        imageService.uploadImage(image);
    }

    @RequestMapping("/api/get/{id}")
    public ResponseEntity<?> returnImage(@PathVariable(name = "id") Long id) throws IOException {
        byte[] img = imageService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpeg")).body(img);
    }
}
