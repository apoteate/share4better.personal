package com.webapp.share4better.controller;

import com.webapp.share4better.model.Food;
import com.webapp.share4better.sevice.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/listView")
public class FoodListController {
    @Autowired
    private IFoodService service;

    @GetMapping(path="/contributedList")
    public @ResponseBody Iterable<Food> getAllContributedFood() {
        // This returns a JSON or XML with the users
        return service.getAllContributedFood(0);
    }

    @GetMapping(path="/receivedList")
    public @ResponseBody Iterable<Food> getAllReceivedFood() {
        // This returns a JSON or XML with the users
        return service.getAllReceivedFood(1);
    }
}
