package com.webapp.share4better.controller;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.sevice.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ProfileListController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileListController.class);

    @Autowired
    private IProfileService service;

    @PostMapping("/validateUser")
    @ResponseBody
    public ResponseEntity getUserProfile(@RequestParam("userEmail") String userEmail, @RequestParam("password") String password) {
        Profile userProfile = new Profile();
        try {
            Iterable<Profile> profiles = service.getUserProfile(userEmail);
            for (Profile profile : profiles) {
                if (profile.getUserPassword().equals(password)) {
                    userProfile = profile;
                    logger.warn("VALID_USER");
                } else {
                    userProfile.setUserEmail(userEmail);
                    logger.warn("INVALID_USER");
                }

            }
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
