package com.webapp.share4better.controller;

import com.webapp.share4better.model.Profile;
import com.webapp.share4better.sevice.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/profileView")
public class ProfileListController {
    @Autowired
    private IProfileService service;

    @GetMapping(path = "/validateUser")
    public @ResponseBody
    Iterable<Profile> getUserNameAndPassword() {
        return service.getUserNameAndPassword("admin");
    }

    @GetMapping(path = "/userProfile")
    public @ResponseBody
    Iterable<Profile> getUserProfile() {
        return service.getUserProfile("admin");
    }
}
