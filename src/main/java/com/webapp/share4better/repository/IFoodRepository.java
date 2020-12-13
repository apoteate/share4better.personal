package com.webapp.share4better.repository;

import com.webapp.share4better.model.Food;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface IFoodRepository extends CrudRepository<Food, Integer> {
    @Query(value="SELECT * FROM food e WHERE e.contributorID = ?1", nativeQuery = true)
    public Iterable<Food> listContributedFoodsForUser(Integer contributorID);

    @Query(value="SELECT * FROM food e WHERE e.receiverID = ?1", nativeQuery = true)
    public Iterable<Food> listReceivedFoodsForUser(Integer receiverID);

    @Query(value="SELECT * from food e where e.receiverID is null and  e.contributorID != ?1", nativeQuery = true)
    public Iterable<Food> listAvailableFoods(Integer userID);

    @Modifying
    @Transactional
    @Query(value = "update food u set u.receiverid = ? where u.id = ?", nativeQuery = true)
    public void updateReceiverId(int receiverid, int id);


}
