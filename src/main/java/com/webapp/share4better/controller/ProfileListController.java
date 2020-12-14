package com.webapp.share4better.controller;


import com.webapp.share4better.model.Profile;
import com.webapp.share4better.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Controller
public class ProfileListController {

    @Autowired
    private IProfileService service;

    @PostMapping("/validateUser")
    public String getUserProfile(@RequestParam("userEmail") String userEmail, @RequestParam("password") String password, HttpServletRequest httpServletRequest) {
        Optional<Profile> profile = service.getUserProfile(userEmail);

        if (profile.isPresent()) {
            if (profile.get().getUser_password().equals(password)) {
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
        Random rand = new Random(System.currentTimeMillis());
        Profile userProfile = new Profile();
        userProfile.setUser_id((int) rand.nextLong());
        userProfile.setUser_name(userName);
        userProfile.setUser_email(userEmail);
        userProfile.setUser_password(password);
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
            produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            headers = "Accept=application/json"
    )
    public ResponseEntity<Profile> getUser(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getSession().getAttribute("userID") != null) {
            int userId = (int) httpServletRequest.getSession().getAttribute("userID");
            Optional<Profile> profile = service.findUserById(userId);
            if (profile.isPresent()) {
                return new ResponseEntity<>(profile.get(), HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(new Profile(), HttpStatus.OK);
    }


    @RequestMapping(
            path = "/updateProfile",
            method = RequestMethod.POST,
            produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            headers = "Accept=application/json"
    )
    public String updateProfileInfo(@RequestParam("fullName") String fullName, @RequestParam("userEmail") String userEmail, @RequestParam("userPass") String userPass, HttpServletRequest httpServletRequest) {

        Integer userID = (Integer) httpServletRequest.getSession().getAttribute("userID");
        if (userID == null) {
            return "redirect:/index.html#id01";
        }
        Profile profileInput = new Profile();

        profileInput.setUser_id(userID);
        profileInput.setUser_name(fullName);
        profileInput.setUser_email(userEmail);
        profileInput.setUser_password(userPass);
        service.addUser(profileInput);

        return "redirect:/personalUpdate.html";

    }

    @PostMapping("/updateImage")
    public String saveImage(@RequestParam("image") MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws IOException {
        int userID = (int) httpServletRequest.getSession().getAttribute("userID");
        Optional<Profile> profile = service.findUserById(userID);
        profile.get().setPhotos(multipartFile.getBytes());
        service.addUser(profile.get()); // This will add the updated user.

        return "redirect:/personalUpdate.html";
    }

    @RequestMapping("/invalidate")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index.html";
    }


}
