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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileListController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileListController.class);

    @Autowired
    private IProfileService service;
    @Autowired
    private AddUserService userService;

    @PostMapping("/validateUser")
    public String getUserProfile(@RequestParam("userEmail") String userEmail, @RequestParam("password") String password, HttpServletRequest httpServletRequest) {
        Profile userProfile = new Profile();
        String redirect = null;
        
            Iterable<Profile> profiles = service.getUserProfile(userEmail);
            for (Profile profile : profiles) {
                if (profile.getUserPassword().equals(password)) {
                    httpServletRequest.getSession().setAttribute("userID", profile.getUserId());
                    return "redirect:/home.html";
                } else {
                    return "redirect:/index.html#id03";

                }

            }
        return "redirect:/index.html#id03";

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

    @RequestMapping("/invalidate")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index.html";
    }

}
