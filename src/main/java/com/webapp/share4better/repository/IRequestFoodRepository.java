package com.webapp.share4better.repository;

import com.webapp.share4better.model.Contact;
import com.webapp.share4better.model.Food;
import com.webapp.share4better.model.RequestFood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface IRequestFoodRepository extends CrudRepository<RequestFood, Integer> {
    @Query(value="SELECT * FROM request_food e WHERE e.receiverid = ?1", nativeQuery = true)
    public Iterable<RequestFood> listAlreadyRequestedFood(Integer contributorID);

}
