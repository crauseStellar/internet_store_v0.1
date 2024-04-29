package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.mapper.GoodMapper;
import kg.mega.internet_store_v1.models.Category;
import kg.mega.internet_store_v1.models.Good;
import kg.mega.internet_store_v1.models.dto.GoodDto;
import kg.mega.internet_store_v1.repository.GoodRepo;
import kg.mega.internet_store_v1.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService {
    private final GoodRepo goodRepo;
    private final GoodMapper goodMapper;

    @Override
    public void saveGood(Good good) {
        goodRepo.save(good);
    }

    @Override
    public Good findById(Long id) {
        return goodRepo.findById(id).orElse(null);
    }

    @Override
    public List<Good> findAll() {
        return goodRepo.findAll();
    }

    @Override
    public GoodDto getByName(String name) {
        Good good = goodRepo.findByName(name).orElse(null);
        GoodDto goodDto = goodMapper.toDto(good);
        return goodDto;
    }

    @Override
    public Good getByRating(double rating) {
        return goodRepo.findByRating(rating);
    }

    @Override
    public List<Good> getAllByRatingAbove(double rating) {
        return goodRepo.findAllByRatingIsGreaterThanEqual(rating);
    }

    @Override
    public List<Good> getAllByCategory(Category category) {
        return goodRepo.findAllByCategory(category);
    }

    @Override
    public Good updateGood(Good good) {
        return goodRepo.save(good);
    }
}
