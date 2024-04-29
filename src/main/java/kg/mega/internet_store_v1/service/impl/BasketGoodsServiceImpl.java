package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.models.Basket;
import kg.mega.internet_store_v1.models.BasketGoods;
import kg.mega.internet_store_v1.repository.BasketGoodsRepo;
import kg.mega.internet_store_v1.service.BasketGoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketGoodsServiceImpl implements BasketGoodsService {
    private final BasketGoodsRepo basketGoodsRepo;
    @Override
    public void saveBasketGoods(BasketGoods basketGoods) {
        basketGoodsRepo.save(basketGoods);
    }

    @Override
    public List<BasketGoods> getAllByBasketAndIsBought(Basket basket, boolean isBought) {
        return basketGoodsRepo.findAllByBasketAndPayed(basket,isBought);
    }

}
