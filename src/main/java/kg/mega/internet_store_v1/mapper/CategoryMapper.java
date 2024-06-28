package kg.mega.internet_store_v1.mapper;

import kg.mega.internet_store_v1.models.Category;
import kg.mega.internet_store_v1.models.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryDto categoryDto);

    @Mappings({
            @Mapping(source = "name", target = "categoryName"),
            @Mapping(source = "isActive", target = "exists")})
    CategoryDto toCategoryDto(Category category);

    List<Category> toCategoryList(List<CategoryDto> categoryDtoList);

    List<CategoryDto> toCategoryDtoList(List<Category> categoryList);
}
