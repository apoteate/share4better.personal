package com.webapp.share4better.controller;

import com.webapp.share4better.model.Food;
import com.webapp.share4better.sevice.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(path="/listView")
public class FoodListController {
    @Autowired
    private IFoodService service;

    @RequestMapping(
            path="/contributedList",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Iterable<Food>> getAllContributedFood() {
        try {
            return new ResponseEntity<Iterable<Food>>(service.getAllContributedFood(0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Food>>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(
            path="/receivedList",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Iterable<Food>> getAllReceivedFood() {
        try {
            return new ResponseEntity<Iterable<Food>>(service.getAllReceivedFood(1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Food>>(HttpStatus.BAD_REQUEST);
        }

    }
}
