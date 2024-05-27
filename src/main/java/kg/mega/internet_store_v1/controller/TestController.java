package kg.mega.internet_store_v1.controller;

import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.models.dto.AuthResponseDto;
import kg.mega.internet_store_v1.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final JwtUtil  jwtUtil;
    @PostMapping("/getToken")
    public String getToken(@RequestBody User user){
        return jwtUtil.generateToken(user);
    }

    @GetMapping("/parseToken")
    public AuthResponseDto parseToken(@RequestParam String token){
        return jwtUtil.getTokenAndData(token);
    }
}
