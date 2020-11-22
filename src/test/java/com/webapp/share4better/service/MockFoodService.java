package com.webapp.share4better.service;

import com.webapp.share4better.model.MockFood;
import com.webapp.share4better.repository.IMockFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockFoodService implements IMockFoodService {
    @Autowired
    private IMockFoodRepository repository;

    @Override
    public Iterable<MockFood> getAllContributedFood(Integer id) {
        return repository.listContributedFoodsForUser(id);
    }

    @Override
    public Iterable<MockFood> getAllReceivedFood(Integer id) {
        return repository.listReceivedFoodsForUser(id);
    }
}
