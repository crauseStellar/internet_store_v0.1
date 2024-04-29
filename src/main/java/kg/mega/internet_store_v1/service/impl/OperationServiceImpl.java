package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.mapper.UserMapper;
import kg.mega.internet_store_v1.models.*;
import kg.mega.internet_store_v1.models.dto.ResponseDto;
import kg.mega.internet_store_v1.repository.OperationRepo;
import kg.mega.internet_store_v1.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepo operationRepo;
    private final UserService userService;
    private final GoodService goodService;
    private final BasketService basketService;
    private final BasketGoodsService basketGoodsService;
    private final UserMapper userMapper;
    @Override
    public Operation createOperation(Operation operation) {

        return null;
    }

    @Override
    public void addToUserBasket(ResponseDto responseDto) {
        Basket basket = basketService.findByUser(responseDto.getUser());
        for(Good g:responseDto.getGood()){
            basketGoodsService.saveBasketGoods(new BasketGoods(basket, g));
        }
//        responseDto.getGood().forEach(good -> basketGoodsService.saveBasketGoods(new BasketGoods(basket,good)));
    }

    @Override
    public ResponseDto getUserBasketData(Long userId, boolean isBought) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setGood(new ArrayList<>());
        User user = userMapper.toEntity(userService.findById(userId));
        Basket basket = basketService.findByUser(user);
//        List<BasketGoods> basketGoodsList = basketGoodsService.findAllBasketGoodsByIsBought(basket.getId(),isBought);

        responseDto.setUser(user);

        return responseDto;
    }
}
