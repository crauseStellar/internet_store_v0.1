package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.enums.OperationType;
import kg.mega.internet_store_v1.mapper.GoodMapper;
import kg.mega.internet_store_v1.mapper.UserMapper;
import kg.mega.internet_store_v1.models.*;
import kg.mega.internet_store_v1.models.dto.GoodDto;
import kg.mega.internet_store_v1.models.dto.RequestDto;
import kg.mega.internet_store_v1.models.dto.ResponseDto;
import kg.mega.internet_store_v1.repository.OperationRepo;
import kg.mega.internet_store_v1.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepo operationRepo;
    private final UserService userService;
    private final GoodService goodService;
    private final BasketService basketService;
    private final BasketGoodsService basketGoodsService;
    private final UserMapper userMapper;
    private final GoodMapper goodMapper;
    @Override
    public Operation createOperation(Operation operation) {

        return null;
    }

    @Override
    public void addToUserBasket(RequestDto requestDto) {
        Basket basket = basketService.findByUser(requestDto.getUser());
        for(Good g:requestDto.getGood()){
            basketGoodsService.saveBasketGoods(new BasketGoods(basket, g));
        }
        Operation operation = new Operation();
        operation.setUser(requestDto.getUser());
        operation.setCreatedDate(LocalDateTime.now());
        operation.setOperationType(OperationType.ADD_TO_CART);
        operationRepo.save(operation);
//        responseDto.getGood().forEach(good -> basketGoodsService.saveBasketGoods(new BasketGoods(basket,good)));
    }

    @Override
    public ResponseDto getUserBasketData(Long userId, boolean isBought) {

        User user = userService.getById(userId);
        Basket basket = basketService.findByUser(user);
        List<BasketGoods> basketGoodsList = basketGoodsService.getAllByBasketAndPayed(basket,isBought);

        return new ResponseDto(userMapper.toDto(user),goodMapper.toDtoList(basketGoodsList.stream().map(BasketGoods::getGood).toList()),isBought);
    }
}
