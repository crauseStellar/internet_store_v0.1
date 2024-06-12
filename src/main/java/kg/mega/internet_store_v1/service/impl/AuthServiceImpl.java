package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.exception.UserNotFoundException;
import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.models.dto.AuthRequestDto;
import kg.mega.internet_store_v1.models.dto.RegistrationRequestDto;
import kg.mega.internet_store_v1.repository.BasketRepo;
import kg.mega.internet_store_v1.service.AuthService;
import kg.mega.internet_store_v1.service.UserService;
import kg.mega.internet_store_v1.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final BasketRepo basketRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> authenticate(AuthRequestDto authRequestDto) {

        try {
            userService.findByUsername(authRequestDto.getUsername())
                    .orElseThrow(() -> new UserNotFoundException(String.format("Ползователя с логином '%s' не существует!", authRequestDto.getUsername())));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(458).body(String.format("Ползователя с логином '%s' не существует!", authRequestDto.getUsername()));
        }
        if (passwordEncoder.matches(authRequestDto.getPassword(), userService.findByUsername(authRequestDto.getUsername()).get().getPassword())) {
            return ResponseEntity.ok(jwtUtil.getTokenAndData(jwtUtil.generateToken(userService.loadUserByUsername(authRequestDto.getUsername()))));
        }
        System.out.println("Invalid password");
        return ResponseEntity.status(459).body("Неправильный пароль!");
    }

    @Override
    public ResponseEntity<?> registrateNewUser(RegistrationRequestDto registrationRequestDto) {
        if (!registrationRequestDto.getPassword().equals(registrationRequestDto.getConfirmPassword())) {
            System.out.println("Введенные пароли несовпадают!");
            return null;
        } else if (userService.findByUsername(registrationRequestDto.getUsername()).isPresent()) {
            System.out.println(String.format("Ползователя с логином '%s' уже существует!", registrationRequestDto.getUsername()));
            return null;
        }
        User user = userService.saveUser(new User(registrationRequestDto));
        return authenticate(new AuthRequestDto(user));
    }
}
