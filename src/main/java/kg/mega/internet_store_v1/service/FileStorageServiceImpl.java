package kg.mega.internet_store_v1.service;

import kg.mega.internet_store_v1.models.Good;
import kg.mega.internet_store_v1.models.Image;
import kg.mega.internet_store_v1.repository.GoodImageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {
    private final GoodService goodService;
    private final GoodImageRepo repo;
    @Override
    public void addImageToGood(MultipartFile image, Long goodId) {
        File newFile = new File("C:\\Users\\iskan\\IdeaProjects\\internet_store_v1\\src\\main\\resources\\good_images\\"+image.getOriginalFilename());
        try {
            newFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(newFile);
            outputStream.write(image.getBytes());
            outputStream.close();
            Good good = goodService.findById(goodId);
            Image goodImage = new Image();
            goodImage.setGood(good);
            goodImage.setPath(newFile.getAbsolutePath());
            repo.save(goodImage);
        } catch (IOException e) {
            System.out.println("Error creating file");;
        }
    }
}
