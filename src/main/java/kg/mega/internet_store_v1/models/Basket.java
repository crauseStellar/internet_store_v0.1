package kg.mega.internet_store_v1.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_basket")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;

    public Basket(User byEmail) {
        this.user=byEmail;
    }

//    @OneToMany
//    private List<Good>good;

}
