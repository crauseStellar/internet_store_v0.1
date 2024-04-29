package kg.mega.internet_store_v1.repository;

import kg.mega.internet_store_v1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
