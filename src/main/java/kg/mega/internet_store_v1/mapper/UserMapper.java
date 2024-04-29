package kg.mega.internet_store_v1.mapper;

import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.models.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements BaseMapper<User, UserDto> {
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setFio(user.getFio());
        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setFio(userDto.getFio());
        return user;
    }

    @Override
    public List<User> toEntityList(List<UserDto> userDtos) {
        return List.of();
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        return List.of();
    }
}
