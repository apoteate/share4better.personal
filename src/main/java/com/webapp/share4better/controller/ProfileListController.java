package com.webapp.share4better.controller;


import com.webapp.share4better.model.Profile;
import com.webapp.share4better.service.IProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ProfileListController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileListController.class);

    @Autowired
    private IProfileService service;

    @PostMapping("/validateUser")
    public String getUserProfile(@RequestParam("userEmail") String userEmail, @RequestParam("password") String password, HttpServletRequest httpServletRequest) {
            Optional<Profile> profile = service.getUserProfile(userEmail);

            if (profile.isPresent()) {
                if (profile.get().getUserPassword().equals(password)) {
                    httpServletRequest.getSession().setAttribute("userID", profile.get().getUser_id());
                    return "redirect:/home.html";
                } else {
                   return "redirect:/index.html#id03";
               }
            }
        return "redirect:/index.html#id03";

    }


    @PostMapping("/signUp")
    public String signupUser(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, @RequestParam("password") String password, HttpServletRequest httpServletRequest) {


        Optional<Profile> profile = service.getUserProfile(userEmail);

        if (profile.isPresent()) {
            return "redirect:/index.html#id04";
        }

        Profile userProfile = new Profile();
        userProfile.setUser_name(userName);
        userProfile.setUser_email(userEmail);
        userProfile.setUserPassword(password);
        userProfile.setDonor_status(true);
        service.addUser(userProfile);

        Optional<Profile> user = service.getUserProfile(userEmail);
        if (user.isPresent()) {
            httpServletRequest.getSession().setAttribute("userID", user.get().getUser_id());
            return "redirect:/home.html";
        } else {
            return "redirect:/index.html#id05";
        }


    }

    @RequestMapping(
            path = "/getUserCurrentUser",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Profile> getUser(HttpServletRequest httpServletRequest) {
        int userId = (int) httpServletRequest.getSession().getAttribute("userID");
        Optional<Profile> profile = service.findUserById(userId);
        if (profile.isPresent()) {
            return new ResponseEntity<>(profile.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Profile(), HttpStatus.OK);
    }

    @RequestMapping("/invalidate")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index.html";
    }



}
