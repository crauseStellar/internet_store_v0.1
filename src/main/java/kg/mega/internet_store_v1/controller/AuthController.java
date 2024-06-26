package kg.mega.internet_store_v1.controller;

import kg.mega.internet_store_v1.models.dto.ActivateUserDto;
import kg.mega.internet_store_v1.models.dto.AuthRequestDto;
import kg.mega.internet_store_v1.models.dto.AuthResponseDto;
import kg.mega.internet_store_v1.models.dto.RegistrationRequestDto;
import kg.mega.internet_store_v1.service.AuthService;
import kg.mega.internet_store_v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    @PostMapping("/authenticate")
    private ResponseEntity<?> authenticate(@RequestBody AuthRequestDto authRequestDto) {
        return authService.authenticate(authRequestDto);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDto requestDto) {
        return authService.registrateNewUser(requestDto);
    }
    @PostMapping("/activation")
    public ResponseEntity<?> activation(@RequestBody ActivateUserDto activateUserDto){
        return userService.activateUser(activateUserDto);
    }
}
