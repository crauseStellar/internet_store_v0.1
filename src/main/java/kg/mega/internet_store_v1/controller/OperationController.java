package kg.mega.internet_store_v1.controller;

import kg.mega.internet_store_v1.models.dto.RequestDto;
import kg.mega.internet_store_v1.models.dto.ResponseDto;
import kg.mega.internet_store_v1.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;
    @PostMapping("/add_to_cart")
    public void addToCart(@RequestBody RequestDto requestDto) {
        operationService.addToUserBasket(requestDto);
    }
    @GetMapping("/get_cart_data")
    public ResponseDto getUserCartData(@RequestParam Long userId,@RequestParam boolean isBought) {
        return operationService.getUserBasketData(userId, isBought);
    }
}
