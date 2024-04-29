package kg.mega.internet_store_v1.service;

import kg.mega.internet_store_v1.models.Basket;
import kg.mega.internet_store_v1.models.User;

public interface BasketService {
    Basket save(Basket basket);
    Basket findByUser(User user);
}
