package kg.mega.internet_store_v1.models;

import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Entity
@Table(name = "tb_category")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isActive;


}
