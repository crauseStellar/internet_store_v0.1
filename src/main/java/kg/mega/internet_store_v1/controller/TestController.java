package kg.mega.internet_store_v1.controller;

import kg.mega.internet_store_v1.models.dto.AuthResponseDto;
import kg.mega.internet_store_v1.service.MailService;
import kg.mega.internet_store_v1.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final JwtUtil  jwtUtil;
    private final MailService mailService;
//    @PostMapping("/getToken")
//    public String getToken(@RequestBody User user){
//        return jwtUtil.generateToken(user);
//    }

    @GetMapping("/parseToken")
    public AuthResponseDto parseToken(@RequestParam String token){
        return jwtUtil.getTokenAndData(token);
    }
    @GetMapping("/testMail")
    public void sendSimpleMessage(@RequestParam String to,@RequestParam String subject,@RequestParam String text){
        mailService.sendSimpleMessage(to,subject,text);
    }
    @GetMapping("/testMailWithAttachment")
    public void sendMimeMessage(@RequestParam String to,@RequestParam String subject,
                                @RequestParam String text,@RequestParam String pathToAttachment){
        mailService.sendMessageWithAttachment(to,subject,text,pathToAttachment);
    }
}
