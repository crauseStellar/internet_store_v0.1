package kg.mega.internet_store_v1.repository;

import kg.mega.internet_store_v1.models.Basket;
import kg.mega.internet_store_v1.models.BasketGoods;
import kg.mega.internet_store_v1.models.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketGoodsRepo extends JpaRepository<BasketGoods,Long> {
//    @Query(value = "select * from tb_basket_goods where basket_id=?1 and is_bought=?2",nativeQuery = true)
    List<BasketGoods> findAllByBasketAndPayed(Basket basket, boolean payed);
    BasketGoods findByBasketAndGood(Basket basket, Good good);
}
