package kg.mega.internet_store_v1.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class GoodDto {
    private String name;
    private double price;
    private double rating;
    private Long quantity;
    private List<String>images;
    private String categoryName;
}
