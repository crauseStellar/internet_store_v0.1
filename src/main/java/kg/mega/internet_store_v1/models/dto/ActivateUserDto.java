package kg.mega.internet_store_v1.models.dto;

import lombok.Data;

@Data
public class ActivateUserDto {
    private String email;
    private Integer activationCode;
}
