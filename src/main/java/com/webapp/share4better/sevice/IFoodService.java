package com.webapp.share4better.sevice;

import com.webapp.share4better.model.Food;

import java.util.List;

public interface IFoodService {
    public Iterable<Food> getAllContributedFood(Integer id);
    public Iterable<Food> getAllReceivedFood(Integer id);
}
