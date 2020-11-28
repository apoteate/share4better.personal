package com.webapp.share4better.controller;

import com.webapp.share4better.model.Address;
import com.webapp.share4better.model.Contact;
import com.webapp.share4better.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class AddressController {
    @Autowired
    private IAddressService service;

    @RequestMapping(
            path = "/getAddressInfo",
            method = RequestMethod.GET,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public ResponseEntity<Address> getAddressInformation(HttpServletRequest httpServletRequest) {
        try {
            int userID = (int) httpServletRequest.getSession().getAttribute("userID");
            Optional<Address> address = service.getAddressInformation(userID);
            if (address.isPresent()) {
                return new ResponseEntity<>(service.getAddressInformation(userID).get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Address(), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            path = "/updateAddressInfo",
            method = RequestMethod.POST,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json"
    )
    public String updateAddressInfo(@RequestParam("homeAddress") String homeAddress, @RequestParam("workAddress") String workAddress, HttpServletRequest httpServletRequest) {

        Integer userID = (Integer) httpServletRequest.getSession().getAttribute("userID");
        if (userID == null) {
            return "redirect:/index.html#id01";
        }
        Address address = new Address();

        address.setHome_address(homeAddress);
        address.setWork_address(homeAddress);
        address.setId(userID);
        service.updateAddressInfo(address);
        return "redirect:/addressUpdate.html";

    }
}
