package com.webapp.share4better.controller;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.webapp.share4better.model.Food;
import com.webapp.share4better.model.Profile;
import com.webapp.share4better.model.ReceiverFoodList;
import com.webapp.share4better.service.IFoodService;
import com.webapp.share4better.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FoodListController {
    @Autowired
    private IFoodService service;
    @Autowired
    private IProfileService profileService;

    @RequestMapping(
            path="/dashboard",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<List<ReceiverFoodList>> getAllContributedFood() {
        try {
            Iterable<Food> foodIterable = service.getAllAvailableFood();

            List<ReceiverFoodList> receiverFoodArrayList = new ArrayList<>();

            for (Food food : foodIterable) {

                ReceiverFoodList receiverFoodList = new ReceiverFoodList();

                receiverFoodList.setId(food.getId());
                receiverFoodList.setContributorID(food.getContributorID());
                receiverFoodList.setName(food.getName());
                receiverFoodList.setQuality(food.getQuality());
                receiverFoodList.setQuantity(food.getQuantity());
                receiverFoodList.setType(food.getType());

                receiverFoodList.setReceiverID(food.getReceiverID());
                String receiverOrContributorName = null;
                if (food.getReceiverID() != null) {
                    Optional<Profile> profile = profileService.findUserById(food.getContributorID());
                    if (profile.isPresent()) {
                        receiverOrContributorName = profile.get().getUser_name();
                    }
                }
                receiverFoodList.setReceiveOrContributorName(receiverOrContributorName);

                receiverFoodArrayList.add(receiverFoodList);
            }

            return new ResponseEntity<>(receiverFoodArrayList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            path="/contributedList",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<List<ReceiverFoodList>> getAllContributedFood(HttpServletRequest httpServletRequest) {
        try {
            int userID = (int) httpServletRequest.getSession().getAttribute("userID");
            Iterable<Food> foodIterable = service.getAllContributedFood(userID);


            List<ReceiverFoodList> receiverFoodArrayList = new ArrayList<>();

            for (Food food : foodIterable) {

                ReceiverFoodList receiverFoodList = new ReceiverFoodList();

                receiverFoodList.setId(food.getId());
                receiverFoodList.setContributorID(food.getContributorID());
                receiverFoodList.setName(food.getName());
                receiverFoodList.setQuality(food.getQuality());
                receiverFoodList.setQuantity(food.getQuantity());
                receiverFoodList.setType(food.getType());

                receiverFoodList.setReceiverID(food.getReceiverID());
                String receiverName = "Not yet received by anyone";
                if (food.getReceiverID() != null) {
                   Optional<Profile> profile = profileService.findUserById(food.getReceiverID());
                    if (profile.isPresent()) {
                        receiverName = profile.get().getUser_name();
                    }
                }
                receiverFoodList.setReceiveOrContributorName(receiverName);

                receiverFoodArrayList.add(receiverFoodList);
            }

            return new ResponseEntity<>(receiverFoodArrayList, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(
            path="/receivedList",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<List<ReceiverFoodList>>  getAllReceivedFood(HttpServletRequest httpServletRequest) {
        try {
            int userID = (int) httpServletRequest.getSession().getAttribute("userID");
            Iterable<Food> foodIterable = service.getAllReceivedFood(userID);

            List<ReceiverFoodList> receiverFoodArrayList = new ArrayList<>();

            for (Food food : foodIterable) {

                ReceiverFoodList receiverFoodList = new ReceiverFoodList();

                receiverFoodList.setId(food.getId());
                receiverFoodList.setContributorID(food.getContributorID());
                receiverFoodList.setName(food.getName());
                receiverFoodList.setQuality(food.getQuality());
                receiverFoodList.setQuantity(food.getQuantity());
                receiverFoodList.setType(food.getType());

                receiverFoodList.setReceiverID(food.getReceiverID());
                String receiverOrContributorName = null;
                if (food.getReceiverID() != null) {
                    Optional<Profile> profile = profileService.findUserById(food.getContributorID());
                    if (profile.isPresent()) {
                        receiverOrContributorName = profile.get().getUser_name();
                    }
                }
                receiverFoodList.setReceiveOrContributorName(receiverOrContributorName);

                receiverFoodArrayList.add(receiverFoodList);
            }



            return new ResponseEntity<>(receiverFoodArrayList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/contribute")
    public void contributeFood(@RequestParam("foodName") String foodName, @RequestParam("foodType") String foodType, @RequestParam("foodQuality") String foodQuality, @RequestParam("foodQuantity") String foodQuantity, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {

        StringBuilder htmlBuilder = new StringBuilder();

            int userID = (int) httpServletRequest.getSession().getAttribute("userID");
            Food food = new Food();
            food.setContributorID(userID);
            food.setName(foodName);
            food.setType(foodType);
            food.setQuality(foodQuality);
            food.setQuantity(foodQuantity);

            Food save = service.addFood(food);
        Optional<Food> savedFood = service.findById(save.getId());

        if (savedFood.isPresent()) {
           // return "id =" + savedFood.get().getId();

            htmlBuilder.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf 8\">\n" +
                    "    <title>Contributed Items</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n" +
                    "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n" +
                    "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "    <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
                    "    <style>\n" +
                    ".loader {\n" +
                    "  border: 16px solid #f3f3f3;\n" +
                    "  border-radius: 50%;\n" +
                    "  border-top: 16px solid #3498db;\n" +
                    "  width: 50px;\n" +
                    "  height: 50px;\n" +
                    "  -webkit-animation: spin 2s linear infinite; /* Safari */\n" +
                    "  animation: spin 2s linear infinite;\n" +
                    "}\n" +
                    "\n" +
                    "/* Safari */\n" +
                    "@-webkit-keyframes spin {\n" +
                    "  0% { -webkit-transform: rotate(0deg); }\n" +
                    "  100% { -webkit-transform: rotate(360deg); }\n" +
                    "}\n" +
                    "\n" +
                    "@keyframes spin {\n" +
                    "  0% { transform: rotate(0deg); }\n" +
                    "  100% { transform: rotate(360deg); }\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "\n" +
                    "\n" +
                    "<nav class=\"navbar navbar-inverse fixed-top\">\n" +
                    "    <div class=\"container-fluid\">\n" +
                    "        <div class=\"navbar-header\">\n" +
                    "            <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#myNavbar\">\n" +
                    "                <span class=\"icon-bar\"></span>\n" +
                    "                <span class=\"icon-bar\"></span>\n" +
                    "                <span class=\"icon-bar\"></span>\n" +
                    "            </button>\n" +
                    "            <a class=\"navbar-brand\" href=\"/home.html\">Share4Better</a>\n" +
                    "        </div>\n" +
                    "        <div class=\"collapse navbar-collapse\" id=\"myNavbar\">\n" +
                    "            <ul class=\"nav navbar-nav navbar-right\">\n" +
                    "                <li><a href=\"/home.html\"><span class=\"glyphicon glyphicon-home\"></span> Home</a></li>\n" +
                    "                <li><a href=\"/invalidate\"><span class=\"glyphicon glyphicon-log-out\"></span> Logout</a></li>\n" +
                    "            </ul>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "\n" +
                    "</nav>\n" +
                    "<div class=\"w3-container w3-center w3-animate-top\">\n" +
                    "    <h1>Please wait while we are adding your contribution as below</h1>\n" +
                    "</div>\n" +
                    "<center><div class=\"loader\"></div></center>\n" +
                    "\n" +
                    "\n" +
                    "<br>\n" +
                    "<br>\n" +
                    "<div class=\"container\">\n" +
                    "\n" +
                    "    <ul class=\"list-group\" ng-repeat=\"item in items\">\n" +
                    "        <li class=\"list-group-item\">"+  savedFood.get().getName() +" <span class=\"badge\">Name</span></li>\n" +
                    "        <li class=\"list-group-item\">" + savedFood.get().getType() + " <span class=\"badge\">Type</span></li>\n" +
                    "        <li class=\"list-group-item\">"+ savedFood.get().getQuality() +" <span class=\"badge\">Quality</span></li>\n" +
                    "        <li class=\"list-group-item\">"+ savedFood.get().getQuantity() + " <span class=\"badge\">Quantity</span></li>\n" +
                    "    </ul>\n" +
                    "</div>\n" +
                    "\n" +
                    "<script>\n" +
                    "         setTimeout(function(){\n" +
                    "            window.location.href = '/home.html#sucsess';\n" +
                    "         }, 5000);\n" +
                    "      </script>\n" +
                    "</body>\n" +
                    "</html>");

            response.getWriter().write(htmlBuilder.toString());

        } else {

        }
    }
}
