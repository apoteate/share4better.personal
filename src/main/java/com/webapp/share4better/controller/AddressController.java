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
    public String updateAddressInfo(@RequestParam("home_address") String home_address, @RequestParam("home_city") String home_city, @RequestParam("home_state") String home_state, @RequestParam("home_zip") int home_zip, @RequestParam("home_country") String home_country, @RequestParam("work_address") String work_address, @RequestParam("work_city") String work_city, @RequestParam("work_state") String work_state, @RequestParam("work_zip") int work_zip,@RequestParam("work_country") String work_country, HttpServletRequest httpServletRequest) {

        Integer userID = (Integer) httpServletRequest.getSession().getAttribute("userID");
        if (userID == null) {
            return "redirect:/index.html#id01";
        }
        Address address = new Address();
        address.setId(userID);
        address.setHome_address(home_address);
        address.setHome_city(home_city);
        address.setHome_state(home_state);
        address.setHome_zip(home_zip);
        address.setHome_country(home_country);

        address.setWork_address(work_address);
        address.setWork_city(work_city);
        address.setWork_state(work_state);
        address.setWork_zip(work_zip);
        address.setWork_country(work_country);

        service.updateAddressInfo(address);
        return "redirect:/addressUpdate.html";

    }
}
