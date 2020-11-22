package com.webapp.share4better.repository;

import com.webapp.share4better.model.MockFood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IMockFoodRepository extends CrudRepository<MockFood, Integer> {
    @Query(value="SELECT * FROM MockFood e WHERE e.contributorID = ?1", nativeQuery = true)
    public Iterable<MockFood> listContributedFoodsForUser(Integer contributorID);

    @Query(value="SELECT * FROM MockFood e WHERE e.receiverID = ?1", nativeQuery = true)
    public Iterable<MockFood> listReceivedFoodsForUser(Integer receiverID);
}
