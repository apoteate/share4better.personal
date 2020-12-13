package com.webapp.share4better.service;

import com.webapp.share4better.model.Food;

import java.util.Optional;

public interface IFoodService {
    public Iterable<Food> getAllContributedFood(Integer id);
    public Iterable<Food> getAllReceivedFood(Integer id);
    public Iterable<Food> getAllAvailableFood(Integer id);
    public Food addFood(Food food);
    public Optional<Food> findById(int i);
    public void updateReceiverId(int receiverid, int id);
}
