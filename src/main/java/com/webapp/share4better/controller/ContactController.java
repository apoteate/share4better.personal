package com.webapp.share4better.controller;

import com.webapp.share4better.model.Contact;
import com.webapp.share4better.service.IContactService;
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
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private IContactService service;

    @RequestMapping(
            path = "/getContact",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Contact> getContactInformation(HttpServletRequest httpServletRequest) {
        Optional<Contact> contact = service.getContactInformation((int) httpServletRequest.getSession().getAttribute("userID"));
        if (contact.isPresent()) {
            return new ResponseEntity<>(contact.get(),HttpStatus.OK);
        } else {
        return new ResponseEntity<>(new Contact(),HttpStatus.OK);
        }
    }

    @RequestMapping(
            path = "/updateContact",
            method = RequestMethod.POST,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public String updateContactInfo(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("additionalNumber") String additionalNumber, HttpServletRequest httpServletRequest) {

       Integer userID = (Integer) httpServletRequest.getSession().getAttribute("userID");
       if (userID == null) {
           return "redirect:/index.html#id01";
       }
        Contact contactInput = new Contact();

        contactInput.setPhone_number(phoneNumber);
        contactInput.setAdditional_number(additionalNumber);
        contactInput.setId(userID);
        service.updateContactInfo(contactInput);

        return "redirect:/contactUpdate.html";

    }
}
