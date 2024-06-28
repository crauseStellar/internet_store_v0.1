package kg.mega.internet_store_v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.mega.internet_store_v1.models.Category;
import kg.mega.internet_store_v1.models.Good;
import kg.mega.internet_store_v1.models.dto.GoodDto;
import kg.mega.internet_store_v1.service.FileStorageService;
import kg.mega.internet_store_v1.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Работа с товарами", description = "Эндпоинты для работы с товарами")
@RestController
@RequestMapping("/api/v1/good")
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;
    private final FileStorageService storageService;
    @Operation(description = "Создание нового товара", summary = "Создать товар")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Товар успешно создан!")
    })
    @PostMapping("/admin/save")
    public void saveGood(@RequestBody Good good){
        goodService.saveGood(good);
    }

    @Operation(description = "Получение товара по ID", summary = "Получить товар")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Товар успешно получен!")
    })
    @GetMapping("/findById/{id}")
    public GoodDto findById(@PathVariable Long id){
        return goodService.getById(id);
    }

    @Operation(description = "Получение списка товаров", summary = "Получить список товаров")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список товаров успешно получен!")
    })
    @GetMapping("/findAll")
    public List<Good> findAll(){
        return goodService.findAll();
    }

    @Operation(description = "Получение товара по названию", summary = "Получить товар по названию")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Товар успешно получен!")
    })
    @GetMapping("/get_by_name")
    public GoodDto getByName(@RequestParam String name){
        return goodService.getByName(name);
    }

    @Operation(description = "Получение товара по рейтингу", summary = "Получить товар по рейтингу")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Товар успешно получен!")
    })
    @GetMapping("/get_by_rating")
    public Good getByName(@RequestParam double rating){
        return goodService.getByRating(rating);
    }

    @Operation(description = "Получение всех товаров по рейтингу выше", summary = "Получить товар(ы) по рейтингу выше")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Товар(ы) успешно получен(ы)!")
    })
    @GetMapping("/get_all_by_rating")
    public List<Good> getAllByName(@RequestParam double rating){
        return goodService.getAllByRatingAbove(rating);
    }

    @Operation(description = "Получение товаров по категории", summary = "Получить товаров по категории")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Товар(ы) успешно получен(ы)!")
    })
    @GetMapping("/get_all_by_category")
    public List<Good> getAllByCategory(@RequestBody Category category){
        return goodService.getAllByCategory(category);}

    @Operation(description = "Обновление  товара", summary = "Обновить товар")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Товар успешно обновлен!")
    })
    @PutMapping("/admin/update")
    public Good updateGood(@RequestBody Good good){
        return goodService.updateGood(good);}

    @Operation(description = "Добавление картинки товара", summary = "Добавить картинку тавара")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Картинка успешно добавлена")
    })
    @PostMapping("/admin/add_image")
    public void addImage(@RequestParam("file")  MultipartFile file,@RequestParam Long id){
        storageService.addImageToGood(file,id);
    }
}