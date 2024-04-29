package kg.mega.internet_store_v1.repository;

import kg.mega.internet_store_v1.models.Operation;
import kg.mega.internet_store_v1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<Operation,Long> {
    List<Operation> findByUser(User user);
}
