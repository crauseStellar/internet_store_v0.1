package kg.mega.internet_store_v1.service;

import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.models.dto.ActivateUserDto;
import kg.mega.internet_store_v1.models.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User saveUser(User user);
    UserDto findById(Long id);
    List<User> findAll();

    User updateUser(User user);

    String delete(User user);
    Optional<User> findByEmail(String email);
    User update(User user);

    User getById(Long userId);

    Optional<User>findByUsername(String username);
    ResponseEntity<?> activateUser(ActivateUserDto activateUserDto);
}
