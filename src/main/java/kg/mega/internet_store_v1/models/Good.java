package kg.mega.internet_store_v1.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tb_good")
@Getter
@Setter
@RequiredArgsConstructor
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private double rating;
    private Long quantity;
    @ManyToOne
    private Category category;


    @Override
    public String toString() {
        return
                "Name: " + name +
                "\nPrice: " + price +
                "\nRating: " + rating+"\n###############################";

    }
}
