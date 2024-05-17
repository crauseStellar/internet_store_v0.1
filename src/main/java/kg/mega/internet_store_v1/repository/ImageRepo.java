package kg.mega.internet_store_v1.repository;

import kg.mega.internet_store_v1.models.Image;
import kg.mega.internet_store_v1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    Optional<Image> findByUser(User user);
}
