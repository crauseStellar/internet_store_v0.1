package kg.mega.internet_store_v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kg.mega.internet_store_v1.models.Category;
import kg.mega.internet_store_v1.models.Good;
import kg.mega.internet_store_v1.models.dto.GoodDto;
import kg.mega.internet_store_v1.service.FileStorageService;
import kg.mega.internet_store_v1.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/good")
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;
    private final FileStorageService storageService;
    @PostMapping("/save")
    public void saveGood(@RequestBody Good good){
         goodService.saveGood(good);
    }
    @GetMapping("/findById/{id}")
    public GoodDto findById(@PathVariable Long id){
        return goodService.getById(id);
    }
    @GetMapping("/findAll")
    public List<Good> findAll(){
        return goodService.findAll();
    }
    @GetMapping("/get_by_name")
    public GoodDto getByName(@RequestParam String name){
        return goodService.getByName(name);
    }
    @GetMapping("/get_by_rating")
    public Good getByName(@RequestParam double rating){
        return goodService.getByRating(rating);
    }
    @GetMapping("/get_all_by_rating")
    public List<Good> getAllByName(@RequestParam double rating){
        return goodService.getAllByRatingAbove(rating);
    }
    @GetMapping("/get_all_by_category")
    public List<Good> getAllByCategory(@RequestBody Category category){
        return goodService.getAllByCategory(category);
    }
    @PutMapping("/update")
    public Good updateGood(@RequestBody Good good){
       return goodService.updateGood(good);
    }
    @Operation(description = "Добавление картинки товара")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Картинка успешно добавлена")
    })
    @PostMapping("/add_image")
    public void addImage(@RequestParam("file")  MultipartFile file,@RequestParam Long id){
        storageService.addImageToGood(file,id);
    }
}
