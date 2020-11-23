package com.webapp.share4better.service;

import com.webapp.share4better.model.Food;

public interface IFoodService {
    public Iterable<Food> getAllContributedFood(Integer id);
    public Iterable<Food> getAllReceivedFood(Integer id);
}
