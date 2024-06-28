package kg.mega.internet_store_v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.mega.internet_store_v1.models.dto.AuthResponseDto;
import kg.mega.internet_store_v1.service.MailService;
import kg.mega.internet_store_v1.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Работа с Токенами", description = "Эндпоинты для работы с токенами")

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final JwtUtil jwtUtil;
    private final MailService mailService;

//    @Operation(description = "Получение токена", summary = "Получить токен")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Токен успешно получен!")
//    })
//    @PostMapping("/getToken")
//    public String getToken(@RequestBody User user){
//        return jwtUtil.generateToken(user);
//    }

    @Operation(description = "Парсинг токена", summary = "Парсить токен")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Токен успешно запарсен!")
    })
    @GetMapping("/parseToken")
    public AuthResponseDto parseToken(@RequestParam String token){
        return jwtUtil.getTokenAndData(token);
    }

    @Operation(description = "Отправка SimpleMessage ", summary = "Отправить SimpleMessage")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SimpleMessage успешно отправлен!")
    })
    @GetMapping("/testMail")
    public void sendSimpleMessage(@RequestParam String to, @RequestParam String subject, @RequestParam String text){
        mailService.sendSimpleMessage(to,subject,text);
    }

    @Operation(description = "Отправка MimeMessage", summary = "Отправить MimeMessage")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "MimeMessage успешно отправлен!")
    })
    @GetMapping("/testMailWithAttachment")
    public void sendMimeMessage(@RequestParam String to, @RequestParam String subject,
                                @RequestParam String text, @RequestParam String pathToAttachment){
        mailService.sendMessageWithAttachment(to,subject,text,pathToAttachment);
    }
}