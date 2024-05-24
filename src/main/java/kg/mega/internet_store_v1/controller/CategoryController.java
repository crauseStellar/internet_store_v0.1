package kg.mega.internet_store_v1.controller;

import kg.mega.internet_store_v1.models.Category;
import kg.mega.internet_store_v1.models.dto.CategoryDto;
import kg.mega.internet_store_v1.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/admin/save")
    public void saveCategory(@RequestBody Category category) {
        categoryService.save(category);
    }
    @GetMapping("/get_all")
    public List<CategoryDto> getAll(){
      return   categoryService.findAll();
    }

}
