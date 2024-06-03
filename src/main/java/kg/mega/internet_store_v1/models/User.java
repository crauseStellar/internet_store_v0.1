package kg.mega.internet_store_v1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_user")
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fio;
    private String email;
    private Boolean isActive;
    @OneToMany(targetEntity = Image.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Image> images;



}
