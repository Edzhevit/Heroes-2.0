package softuni.heroes.service.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    String upload (MultipartFile multipartFile) throws IOException;
}
