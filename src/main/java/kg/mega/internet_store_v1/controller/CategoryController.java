package kg.mega.internet_store_v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.mega.internet_store_v1.models.Category;
import kg.mega.internet_store_v1.models.dto.CategoryDto;
import kg.mega.internet_store_v1.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Работа с категориями товаров", description = "Эндпоинты для работы с категориями")
@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(description = "Создание новой категории", summary = "Создать категорию")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Категория успешно создана!")
    })
    @PostMapping("/admin/save")
    public void saveCategory(@RequestBody Category category) {
        categoryService.save(category);
    }

    @Operation(description = "Получение списка категорий", summary = "Получить список категорий")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список товаров успешно получен!")
    })
    @GetMapping("/get_all")
    public List<CategoryDto> getAll(){
        return   categoryService.findAll();
    }

}
