package com.webapp.share4better.controller;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.model.User;
import com.webapp.share4better.sevice.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
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
    public ResponseEntity getUserNameAndPassword(@RequestParam("userName") String userName,
                                                 @RequestParam("password") String password) {
        User user = new User();
        try {
            Iterable<Profile> profiles = service.getUserNameAndPassword(userName);
            for (Profile profile : profiles) {
                if (profile.getUserPassword().equals(password)) {
                    user.setValidUser(true);
                    user.setUserName(userName);
                    user.setUserId(profile.getUserId());
                    logger.warn( "VALID_USER");
                } else {
                    user.setUserName(userName);
                    user.setValidUser(false);
                    logger.warn( "INVALID_USER");
                }

            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            path="/userProfile",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Iterable<Profile>> getUserProfile() {
        try {
            return new ResponseEntity<>(service.getUserNameAndPassword("admin"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
