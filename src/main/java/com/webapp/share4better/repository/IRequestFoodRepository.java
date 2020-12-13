package com.webapp.share4better.repository;

import com.webapp.share4better.model.Contact;
import com.webapp.share4better.model.Food;
import com.webapp.share4better.model.RequestFood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface IRequestFoodRepository extends CrudRepository<RequestFood, Integer> {
    @Query(value="SELECT * FROM request_food e WHERE e.receiverid = ?1", nativeQuery = true)
    public Iterable<RequestFood> listAlreadyRequestedFood(Integer contributorID);

    @Query(value="SELECT * FROM request_food e WHERE e.food_id = ?1", nativeQuery = true)
    public Iterable<RequestFood> findFoodByFoodId(Integer foodId);

}
