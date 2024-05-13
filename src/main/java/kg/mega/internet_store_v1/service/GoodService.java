package kg.mega.internet_store_v1.service;

import kg.mega.internet_store_v1.models.Category;
import kg.mega.internet_store_v1.models.Good;
import kg.mega.internet_store_v1.models.dto.GoodDto;
import org.apache.catalina.LifecycleState;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodService {
//    int saveGood(Good good);
    void saveGood(Good good);
    Good findById(Long id);
    GoodDto getById(Long id);
    List<Good> findAll();
    GoodDto getByName(String name);
    Good getByRating(double rating);
    List<Good>getAllByRatingAbove(double rating);
    List<Good> getAllByCategory(Category category);
    Good updateGood(Good good);

    void addImage(MultipartFile file, Long id);
}
