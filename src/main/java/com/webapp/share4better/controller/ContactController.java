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
public class ContactController {
    @Autowired
    private IContactService service;

    @RequestMapping(
            path = "/getContact",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Contact> getContactInformation(HttpServletRequest httpServletRequest) {
        try {
            int userID = (int) httpServletRequest.getSession().getAttribute("userID");
            Iterable<Contact> contacts = service.getContactInformation(userID);
            for (Contact contact: contacts) {
                if (contact.getPhone_number() != null) {
                    return new ResponseEntity<>(contact,HttpStatus.OK);
                }
            }
            return  new ResponseEntity<>(new Contact(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/updateContact")
    public ResponseEntity<Contact> updateContactInformation(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("additionalNumber") String additionalNumber, HttpServletRequest httpServletRequest) {
        try {
            int userID = (int) httpServletRequest.getSession().getAttribute("userID");
            Iterable<Contact> contacts = service.getContactInformation(userID);
            for (Contact contact: contacts) {
                if (contact.getId() != null) {



                }
            }
            return new ResponseEntity<>(new Contact(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
