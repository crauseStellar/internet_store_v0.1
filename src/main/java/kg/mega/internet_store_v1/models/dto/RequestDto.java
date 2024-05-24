package kg.mega.internet_store_v1.models.dto;

import kg.mega.internet_store_v1.models.Good;
import kg.mega.internet_store_v1.models.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Data
@RequiredArgsConstructor
public class RequestDto {
    private User user;
    private List<Good> good;
    private boolean isBought;
}
