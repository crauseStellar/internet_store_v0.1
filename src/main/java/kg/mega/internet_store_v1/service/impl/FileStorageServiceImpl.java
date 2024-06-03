package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.enums.ImageType;
import kg.mega.internet_store_v1.models.Good;
import kg.mega.internet_store_v1.models.Image;
import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.repository.ImageRepo;
import kg.mega.internet_store_v1.service.FileStorageService;
import kg.mega.internet_store_v1.service.GoodService;
import kg.mega.internet_store_v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {
    private final GoodService goodService;
    private final ImageRepo repo;
    private final UserService userService;
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
//            goodImage.setActive(true);
            repo.save(goodImage);
        } catch (IOException e) {
            System.out.println("Error creating file");;
        }
    }

    @Override
    public void addUserAvatar(MultipartFile avatar, Long userId) {
        File newFile = new File("C:\\Users\\iskan\\IdeaProjects\\internet_store_v1\\src\\main\\resources\\good_images\\"+avatar.getOriginalFilename());
        try {
            newFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(newFile);
            outputStream.write(avatar.getBytes());
            outputStream.close();
            User user = userService.getById(userId);
            Image userAvatar = new Image();
            userAvatar.setType(ImageType.USER_AVATAR);
            userAvatar.setUser(user);
            userAvatar.setPath(newFile.getAbsolutePath());
            setFalseUserAvatarActivity(user);
//            userAvatar.setActive(true);
            repo.save(userAvatar);
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }
    private void setFalseUserAvatarActivity(User user){
        Optional<Image> avatar = repo.findByUser(user);
        if(avatar.isPresent()){
//            avatar.get().setActive(false);
            repo.save(avatar.get());
        }else {
            System.out.println("User has no avatar yet");
        }

    }
}
