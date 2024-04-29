package kg.mega.internet_store_v1.controller;

import kg.mega.internet_store_v1.models.Basket;
import kg.mega.internet_store_v1.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;
    @PostMapping("/save")
    public void saveBasket(@RequestBody Basket basket){
        basketService.save(basket);
    }
}
