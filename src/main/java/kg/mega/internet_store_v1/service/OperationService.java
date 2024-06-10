package kg.mega.internet_store_v1.service;

import kg.mega.internet_store_v1.models.Operation;
import kg.mega.internet_store_v1.models.dto.RequestDto;
import kg.mega.internet_store_v1.models.dto.ResponseDto;

public interface OperationService {
    Operation createOperation(Operation operation);
    void addToUserBasket(RequestDto requestDto);
    ResponseDto getUserBasketData(Long userId, boolean isBought);
    void buy(Long userId, Long productId);
}
