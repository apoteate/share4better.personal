package com.webapp.share4better.repository;

import com.webapp.share4better.model.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IFoodRepository extends CrudRepository<Food, Integer> {
    @Query(value="SELECT * FROM Food e WHERE e.contributorID = ?1", nativeQuery = true)
    public Iterable<Food> listContributedFoodsForUser(Integer contributorID);

    @Query(value="SELECT * FROM Food e WHERE e.receiverID = ?1", nativeQuery = true)
    public Iterable<Food> listReceivedFoodsForUser(Integer receiverID);
}
