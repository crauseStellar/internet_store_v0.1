package kg.mega.internet_store_v1.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AuthResponseDto {
    private String username;
    private String token;
    private Date expires;
}
