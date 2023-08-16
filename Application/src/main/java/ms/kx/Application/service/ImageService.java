package ms.kx.Application.service;

import lombok.AllArgsConstructor;
import ms.kx.Application.entity.Image;
import ms.kx.Application.repository.ImageRepository;
import ms.kx.Application.util.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepository repository;

    public void uploadImage(MultipartFile multiPartFile) throws IOException {
        Image image = repository.save(Image.builder()
                        .name(multiPartFile.getOriginalFilename())
                        .image(ImageUtil.compressImage(multiPartFile.getBytes()))
                        .date(new Date())
                .build());
    }

    public byte[] downloadImage(Long id){
        Optional<Image> image = repository.findById(id);
        return ImageUtil.decompressImage(image.get().getImage());
    }
}
