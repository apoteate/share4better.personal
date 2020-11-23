package com.webapp.share4better.controller;

import com.webapp.share4better.model.Contact;
import com.webapp.share4better.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping(path="/listView")
public class ContactController {
    @Autowired
    private IContactService service;

    @RequestMapping(
            path = "/ContactUpdate",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Iterable<Contact>> getContactInformation(HttpServletRequest httpServletRequest) {
        try {
            int userID = (int) httpServletRequest.getSession().getAttribute("userID");
            return new ResponseEntity<>(service.getContactInformation(userID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
