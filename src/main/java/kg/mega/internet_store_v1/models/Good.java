package kg.mega.internet_store_v1.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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
    @OneToMany(targetEntity = Image.class)
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    private List<Image> images;



    @Override
    public String toString() {
        return
                "Name: " + name +
                "\nPrice: " + price +
                "\nRating: " + rating+"\n###############################";

    }
}
