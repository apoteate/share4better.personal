package com.webapp.share4better.service;

import com.webapp.share4better.model.Food;
import com.webapp.share4better.repository.IFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService implements IFoodService {
    @Autowired
    private IFoodRepository repository;

    @Override
    public Iterable<Food> getAllContributedFood(Integer id) {
        return repository.listContributedFoodsForUser(id);
    }

    @Override
    public Iterable<Food> getAllReceivedFood(Integer id) {
        return repository.listReceivedFoodsForUser(id);
    }

    public Food addFood(Food food) {
      return repository.save(food);
    }

    public Optional<Food> findById(int i) {
        return repository.findById(i);
    }

}
