package kg.mega.internet_store_v1.repository;

import kg.mega.internet_store_v1.models.Basket;
import kg.mega.internet_store_v1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepo extends JpaRepository<Basket,Long> {
    Optional<Basket>findByUserId(Long userId);
    Optional<Basket>findByUser(User user);
}
