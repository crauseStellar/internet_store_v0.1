package kg.mega.internet_store_v1.repository;

import kg.mega.internet_store_v1.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodImageRepo extends JpaRepository<Image, Long> {
}
