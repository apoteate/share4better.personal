package com.webapp.share4better.controller;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.sevice.AddUserService;
import com.webapp.share4better.sevice.IProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileListController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileListController.class);

    @Autowired
    private IProfileService service;
    @Autowired
    private AddUserService userService;

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


    @PostMapping("/signUp")
    @ResponseBody
    public ResponseEntity signupUser(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, @RequestParam("password") String password) {
        Profile userProfile = new Profile();
        userProfile.setUserName(userName);
        userProfile.setUserEmail(userEmail);
        userProfile.setUserPassword(password);
        userProfile.setDonorStatus(true);

        userService.insertWithQuery(userProfile);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }


}
