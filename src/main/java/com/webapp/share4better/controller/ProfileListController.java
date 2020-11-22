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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileListController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileListController.class);

    @Autowired
    private IProfileService service;
    @Autowired
    private AddUserService userService;

    @PostMapping("/validateUser")
    public String getUserProfile(@RequestParam("userEmail") String userEmail, @RequestParam("password") String password, HttpServletRequest httpServletRequest,Model model) {
        Profile userProfile = new Profile();
        
            Iterable<Profile> profiles = service.getUserProfile(userEmail);
            for (Profile profile : profiles) {
                if (profile.getUserPassword().equals(password)) {
                    @SuppressWarnings("unchecked")
                    List<String> messages = (List<String>) httpServletRequest.getSession().getAttribute("USER");
                    if (messages == null) {
                        messages = new ArrayList<>();
                        httpServletRequest.getSession().setAttribute("USER", messages);
                    }
                    messages.add(userEmail);
                    httpServletRequest.getSession().setAttribute("USER", messages);
                    model.addAttribute("user", messages);

                    return "redirect:/welcome.html";
                } else {
                    return "redirect:/index.html#id03";
                }

            }
        return "redirect:/index.html#id03";

    }

    @GetMapping("/")
    public String process(Model model, HttpSession session) {

        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("USER");

        if (messages == null) {
            messages = new ArrayList<>();
        }


        return "welcome";
    }


    @PostMapping("/signUp")
    public String signupUser(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, @RequestParam("password") String password) {


        Iterable<Profile> profiles = service.getUserProfile(userEmail);
        for (Profile profile : profiles) {
            if (profile.getUserEmail().equals(userEmail)) {
                return "redirect:/index.html#id04";
            } else {
                Profile userProfile = new Profile();
                userProfile.setUserName(userName);
                userProfile.setUserEmail(userEmail);
                userProfile.setUserPassword(password);
                userProfile.setDonorStatus(true);
                userService.insertWithQuery(userProfile);
            }

        }

        return "redirect:/index.html#id05";
    }


}
