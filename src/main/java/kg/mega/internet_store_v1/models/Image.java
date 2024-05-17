package kg.mega.internet_store_v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kg.mega.internet_store_v1.enums.ImageType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_images")
@Getter
@Setter
@RequiredArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    @ManyToOne
    @JsonIgnore
    private Good good;
    @ManyToOne
    @JsonIgnore
    private User user;
    private ImageType type;
    private boolean isActive;
}
