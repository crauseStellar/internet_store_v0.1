package kg.mega.internet_store_v1.models.dto;

import kg.mega.internet_store_v1.models.User;
import lombok.Data;

@Data
public class AuthRequestDto {
    private String username;
    private String password;

    public AuthRequestDto() {
    }

    public AuthRequestDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
