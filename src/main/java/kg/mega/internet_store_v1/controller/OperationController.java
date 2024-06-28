package kg.mega.internet_store_v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.mega.internet_store_v1.models.dto.RequestDto;
import kg.mega.internet_store_v1.models.dto.ResponseDto;
import kg.mega.internet_store_v1.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Работа с операциями", description = "Эндпоинты для работы с операциями")
@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;

    @Operation(description = "Добавление товара в корзину", summary = "Добавить товар в корзину")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Товар успешно добавлен в корзину!")
    })
    @PostMapping("/add_to_cart")
    public void addToCart(@RequestBody RequestDto requestDto) {
        operationService.addToUserBasket(requestDto);
    }

    @Operation(description = "Получение данные корзины", summary = "Получить данные корзины")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Данные корзины успешно получены!")
    })
    @GetMapping("/get_cart_data")
    public ResponseDto getUserCartData(@RequestParam Long userId,@RequestParam boolean isBought) {
        return operationService.getUserBasketData(userId, isBought);
    }

    @Operation(description = "Покупка товара", summary = "Купить товар")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Товар успешно куплен!")
    })
    @PostMapping("/buy")
    public void buy(@RequestParam Long userId,@RequestParam Long goodId) {
        operationService.buy(userId, goodId);
    }
}
