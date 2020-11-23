package com.webapp.share4better.controller;

import com.webapp.share4better.model.Address;
import com.webapp.share4better.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddressController {
    @Autowired
    private IAddressService service;

    @RequestMapping(
            path = "/addressUpdate",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Iterable<Address>> getAddressInformation(HttpServletRequest httpServletRequest) {
        try {
            int userID = (int) httpServletRequest.getSession().getAttribute("userID");
            return new ResponseEntity<Iterable<Address>>(service.getAddressInformation(userID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Address>>(HttpStatus.BAD_REQUEST);
        }
    }
}
