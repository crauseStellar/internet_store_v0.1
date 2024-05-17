package kg.mega.internet_store_v1.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    void addImageToGood(MultipartFile image,Long goodId);
    void addUserAvatar(MultipartFile avatar,Long userId);
}
