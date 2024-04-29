package kg.mega.internet_store_v1.service;

import kg.mega.internet_store_v1.models.Basket;
import kg.mega.internet_store_v1.models.BasketGoods;

import java.util.List;

public interface BasketGoodsService {
    void saveBasketGoods(BasketGoods basketGoods);
    List<BasketGoods>getAllByBasketAndIsBought(Basket basket, boolean isBought);

}
