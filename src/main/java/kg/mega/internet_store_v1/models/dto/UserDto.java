package kg.mega.internet_store_v1.models.dto;

import kg.mega.internet_store_v1.models.User;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String fio;
    private String email;


    public UserDto(User user) {

        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fio = user.getFio();
        this.email = user.getEmail();
    }

    public UserDto() {

    }
}
