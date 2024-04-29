package kg.mega.internet_store_v1.repository;

import kg.mega.internet_store_v1.models.Category;
import kg.mega.internet_store_v1.models.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodRepo extends JpaRepository<Good,Long> {
    Optional<Good>findByName(String name);
    Good findByRating(double rating);
//    @Query(value = "select g from Good g where g.rating>=:r")
    List<Good> findAllByRatingIsGreaterThanEqual(double rating);
    List<Good>findAllByCategory(Category category);

}
