package com.webapp.share4better.controller;

import com.webapp.share4better.model.Food;
import com.webapp.share4better.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FoodListController {
    @Autowired
    private IFoodService service;

    @RequestMapping(
            path="/contributedList",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Iterable<Food>> getAllContributedFood(HttpServletRequest httpServletRequest) {
        try {
            int userID = (int) httpServletRequest.getSession().getAttribute("userID");
            return new ResponseEntity<Iterable<Food>>(service.getAllContributedFood(userID), HttpStatus.OK);
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
    public ResponseEntity<Iterable<Food>> getAllReceivedFood(HttpServletRequest httpServletRequest) {
        try {
            int userID = (int) httpServletRequest.getSession().getAttribute("userID");
            return new ResponseEntity<Iterable<Food>>(service.getAllReceivedFood(userID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Food>>(HttpStatus.BAD_REQUEST);
        }

    }
}
