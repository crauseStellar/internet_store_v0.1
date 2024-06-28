package kg.mega.internet_store_v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.mega.internet_store_v1.models.dto.ActivateUserDto;
import kg.mega.internet_store_v1.models.dto.AuthRequestDto;
import kg.mega.internet_store_v1.models.dto.AuthResponseDto;
import kg.mega.internet_store_v1.models.dto.RegistrationRequestDto;
import kg.mega.internet_store_v1.service.AuthService;
import kg.mega.internet_store_v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Работа с авторизацией", description = "Эндпоинты для работы с авторизацией")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Operation(description = "Авторизация пользователя", summary = "Авторизовать пользователя")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно авторизован!")
    })
    @PostMapping("/authenticate")
    private AuthResponseDto authenticate(@RequestBody AuthRequestDto authRequestDto) {
        return authService.authenticate(authRequestDto);
    }

    @Operation(description = "Регистрация пользователя", summary = "Зарегистрировать пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегистрирован!")
    })
    @PostMapping("/registration")
    public AuthResponseDto register(@RequestBody RegistrationRequestDto requestDto) {
        return authService.registrateNewUser(requestDto);
    }

    @Operation(description = "Активация пользователя", summary = "Активировать пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно активирован!")
    })
    @PostMapping("/activation")
    public ResponseEntity<?> activation (@RequestBody ActivateUserDto activateUserDto){
        return userService.activateUser(activateUserDto);
    }
}