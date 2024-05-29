package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.exception.UserNotFoundException;
import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.models.dto.AuthRequestDto;
import kg.mega.internet_store_v1.models.dto.AuthResponseDto;
import kg.mega.internet_store_v1.models.dto.RegistrationRequestDto;
import kg.mega.internet_store_v1.repository.BasketRepo;
import kg.mega.internet_store_v1.service.AuthService;
import kg.mega.internet_store_v1.service.UserService;
import kg.mega.internet_store_v1.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final BasketRepo basketRepo;

    @Override
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) {
        User user=null;
        try {
             user = userService.findByUsername(authRequestDto.getUsername())
                    .orElseThrow(()->new UserNotFoundException(String.format("Ползователя с логином '%s' несуществует!",authRequestDto.getUsername())));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return jwtUtil.getTokenAndData(jwtUtil.generateToken(user));
    }

    @Override
    public AuthResponseDto registrateNewUser(RegistrationRequestDto registrationRequestDto) {
        if (!registrationRequestDto.getPassword().equals(registrationRequestDto.getConfirmPassword())){
            System.out.println("Введенные пароли несовпадают!");
            return null;
        } else if (userService.findByUsername(registrationRequestDto.getUsername()).isPresent()) {
            System.out.println(String.format("Ползователя с логином '%s' уже существует!",registrationRequestDto.getUsername()));
            return null;
        }
        User user = userService.saveUser(new User(registrationRequestDto));
        return authenticate(new AuthRequestDto(user));
    }
}
