package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.mapper.UserMapper;
import kg.mega.internet_store_v1.models.Basket;
import kg.mega.internet_store_v1.models.dto.ResponseDto;
import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.models.dto.UserDto;
import kg.mega.internet_store_v1.repository.UserRepo;
import kg.mega.internet_store_v1.service.BasketService;
import kg.mega.internet_store_v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final BasketService basketService;
    private final UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        if (findByEmail(user.getEmail()).isPresent()) {
            System.out.println("User already exists");
        } else {
            userRepo.save(user);
            basketService.save(new Basket(findByEmail(user.getEmail()).get()));
        }
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepo.findById(id).orElse(null));
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public String delete(User user) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User update(User user) {
        return userRepo.save(user);
    }

    private void test(ResponseDto responseDto){
        responseDto.getUser().setUsername("sdafsdf");
        responseDto.getGood().get(0);
    }

}
