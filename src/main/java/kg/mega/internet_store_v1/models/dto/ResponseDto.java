package kg.mega.internet_store_v1.models.dto;

import kg.mega.internet_store_v1.models.BasketGoods;
import kg.mega.internet_store_v1.models.Good;
import kg.mega.internet_store_v1.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private UserDto user;
    private List<GoodDto> good;
    private boolean isBought;


}
