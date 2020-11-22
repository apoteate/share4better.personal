package com.webapp.share4better.service;

import com.webapp.share4better.model.MockFood;

public interface IMockFoodService {
    public Iterable<MockFood> getAllContributedFood(Integer id);
    public Iterable<MockFood> getAllReceivedFood(Integer id);
}
