package kg.mega.internet_store_v1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_basket_goods")
@Getter
@Setter
@RequiredArgsConstructor
public class BasketGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Basket basket;
    @ManyToOne
    private Good good;
    private Boolean payed;

    public BasketGoods(Basket basket, Good good) {
        this.basket=basket;
        this.good=good;
    }
}
