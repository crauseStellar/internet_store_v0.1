package kg.mega.internet_store_v1.models.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto {
    private String fio;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
}
