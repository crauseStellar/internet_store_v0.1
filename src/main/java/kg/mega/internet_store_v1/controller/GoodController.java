package kg.mega.internet_store_v1.controller;

import kg.mega.internet_store_v1.models.Category;
import kg.mega.internet_store_v1.models.Good;
import kg.mega.internet_store_v1.models.dto.GoodDto;
import kg.mega.internet_store_v1.service.GoodService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/good")
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;
    @PostMapping("/save")
    public void saveGood(@RequestBody Good good){
         goodService.saveGood(good);
    }
    @GetMapping("/findById/{id}")
    public Good findById(@PathVariable Long id){
        return goodService.findById(id);
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
}
