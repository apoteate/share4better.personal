package com.webapp.share4better.controller;

import com.webapp.share4better.model.*;
import com.webapp.share4better.repository.IFoodRepository;
import com.webapp.share4better.repository.IRequestFoodRepository;
import com.webapp.share4better.service.IFoodService;
import com.webapp.share4better.service.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

import com.webapp.share4better.model.Address;
import com.webapp.share4better.service.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
public class FoodListControllerTest {

    @Autowired
    private FoodListController foodListController;

    @Autowired
    private IFoodService foodService;

    @Autowired
    private IFoodRepository foodRepository;

    @Autowired
    private IRequestFoodRepository requestFoodRepository;

    @Autowired
    private TestUtil testUtil;

    @BeforeEach
    public void setUpStart() {
        testUtil.addFoodListTestData();

    }

    @AfterEach
    public void setUpEnd(){
        testUtil.removeFoodListTestData();
        testUtil.removeBooking();
    }


    @Test
    public void contributeFood() throws IOException {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(9999992);
        foodListController.contributeFood("cookies", "snack", "fresh", "20", mockRequest,  mockResponse);
        Iterable<Food> retrieveFoodFromDb = foodService.getAllContributedFood(9999992);

        for (Food food : retrieveFoodFromDb) {

            assertEquals("Food Name: ", "cookies", food.getName());
            assertEquals("Food Type: ", "snack", food.getType());
            assertEquals("Food Quality: ", "fresh", food.getQuality());
            assertEquals("Food Quantity: ", "20", food.getQuantity());
            foodRepository.deleteById(food.getId());
        }
    }

    @Test
    public void getAllContributedFoods() {

        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(9999);
        List<ReceiverFoodList> foodList = foodListController.getAllContributedFoods(mockRequest).getBody();

        assertEquals("", 9999, foodList.get(0).getContributorID());
        assertEquals("", 8888, foodList.get(0).getReceiverID());
        assertEquals("", "bagels", foodList.get(0).getName());
        assertEquals("", "bread", foodList.get(0).getType());
        assertEquals("", "10", foodList.get(0).getQuantity());
        assertEquals("", "fresh", foodList.get(0).getQuality());


    }

    @Test
    public void getMyPendingFoods() throws IOException {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(9999);
        Iterable<Food> foodIterable = foodService.getAllAvailableFood(9999);

        for (Food food:foodIterable){
            if (food.getContributorID().equals(9991)){
                foodListController.requestFoodBooking(food.getId(),mockRequest,mockResponse);
                List<ReceiverFoodList> foodList = foodListController.getMyPendingFoods(mockRequest).getBody();
                assertEquals("", 9991, foodList.get(1).getContributorID());
                assertEquals("", 8881, foodList.get(1).getReceiverID());
                assertEquals("", "squash", foodList.get(1).getName());
                assertEquals("", "vegetable", foodList.get(1).getType());
                assertEquals("", "5", foodList.get(1).getQuantity());
                assertEquals("", "good", foodList.get(1).getQuality());

            }
        }


    }

    @Test
    public void getAllReceivedFood () throws IOException {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(9999);
        Iterable<Food> foodIterable = foodService.getAllReceivedFood(9999);

        for (Food food:foodIterable){
            if (food.getContributorID().equals(9991)){
                foodListController.requestFoodBooking(food.getId(),mockRequest,mockResponse);
                List<ReceiverFoodList> foodList = foodListController.getAllReceivedFood(mockRequest).getBody();
                assertEquals("", 9991, foodList.get(1).getContributorID());
                assertEquals("", 8881, foodList.get(1).getReceiverID());
                assertEquals("", "squash", foodList.get(1).getName());
                assertEquals("", "vegetable", foodList.get(1).getType());
                assertEquals("", "5", foodList.get(1).getQuantity());
                assertEquals("", "good", foodList.get(1).getQuality());

            }
        }
    }

    @Test
    public void requestFoodBooking() throws IOException {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class, RETURNS_DEEP_STUBS);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class, RETURNS_DEEP_STUBS);

        when(mockRequest.getSession().getAttribute("userID")).thenReturn(9999);
        Iterable<Food> foodIterable = foodService.getAllAvailableFood(9999);

        for (Food food:foodIterable){
            if (food.getContributorID().equals(9991)){
                foodListController.requestFoodBooking(food.getId(),mockRequest,mockResponse);
                List<ReceiverFoodList> foodList = foodListController.getMyPendingFoods(mockRequest).getBody();
                assertEquals("", 9991, foodList.get(1).getContributorID());
                assertEquals("", 8881, foodList.get(1).getReceiverID());
                assertEquals("", "squash", foodList.get(1).getName());
                assertEquals("", "vegetable", foodList.get(1).getType());
                assertEquals("", "5", foodList.get(1).getQuantity());
                assertEquals("", "good", foodList.get(1).getQuality());
            }
        }
    }


}

