package com.webapp.share4better.repository;

import com.webapp.share4better.model.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFoodRepository extends CrudRepository<Food, Integer> {
    @Query("SELECT e FROM Food e WHERE e.contributorID = :contributorID")
    public Iterable<Food> listContributedFoodsForUser(@Param("contributorID") Integer contributorID);

    @Query("SELECT e FROM Food e WHERE e.receiverID = :receiverID")
    public Iterable<Food> listReceivedFoodsForUser(@Param("receiverID") Integer receiverID);
}
