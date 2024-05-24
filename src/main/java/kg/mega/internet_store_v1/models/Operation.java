package kg.mega.internet_store_v1.models;

import jakarta.persistence.*;
import kg.mega.internet_store_v1.enums.OperationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "tb_operations")
@Getter
@Setter
@RequiredArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdDate;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
}
