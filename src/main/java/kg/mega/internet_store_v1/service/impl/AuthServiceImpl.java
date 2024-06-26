package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.exception.UserNotFoundException;
import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.models.dto.AuthRequestDto;
import kg.mega.internet_store_v1.models.dto.RegistrationRequestDto;
import kg.mega.internet_store_v1.repository.BasketRepo;
import kg.mega.internet_store_v1.service.AuthService;
import kg.mega.internet_store_v1.service.MailService;
import kg.mega.internet_store_v1.service.UserService;
import kg.mega.internet_store_v1.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final BasketRepo basketRepo;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Override
    public ResponseEntity<?> authenticate(AuthRequestDto authRequestDto) {
        log.info("Auth attempt with data {}", authRequestDto);
        try {
            userService.findByUsername(authRequestDto.getUsername())
                    .orElseThrow(() -> new UserNotFoundException(String.format("Ползователя с логином '%s' не существует!", authRequestDto.getUsername())));
            log.error("There is no user with username '{}'.", authRequestDto.getUsername());
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
        User user = new User(registrationRequestDto);
        user.setActivationCode(generateRandomActivationCode());
        userService.saveUser(user);
        mailService.sendSimpleMessage(user.getEmail(),"Код активации аккаунта","Ваш код активации аккаунта "+user.getActivationCode());
        return authenticate(new AuthRequestDto(new User(registrationRequestDto)));
    }

    private Integer generateRandomActivationCode() {
        return (int) (Math.random() * 10000);
    }
}
