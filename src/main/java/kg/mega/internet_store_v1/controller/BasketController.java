package kg.mega.internet_store_v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.mega.internet_store_v1.models.Basket;
import kg.mega.internet_store_v1.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Работа с корзинами", description = "Эндпоинты для работы с корзинами")
@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @Operation(description = "Создание новой корзинки", summary = "Создать корзину")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Корзина успешно создана!")
    })
    @PostMapping("/save")
    public void saveBasket(@RequestBody Basket basket){
        basketService.save(basket);
    }
}
