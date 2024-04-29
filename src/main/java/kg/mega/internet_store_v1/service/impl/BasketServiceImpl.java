package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.models.Basket;
import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.repository.BasketRepo;
import kg.mega.internet_store_v1.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepo basketRepo;
    @Override
    public Basket save(Basket basket) {
        return basketRepo.save(basket);
    }

    @Override
    public Basket findByUser(User user) {
        return basketRepo.findByUser(user).orElse(null);
    }
}
