package kg.mega.internet_store_v1.service;

import kg.mega.internet_store_v1.models.Category;
import kg.mega.internet_store_v1.models.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    void save(Category category);
    List<CategoryDto> findAll();
}
