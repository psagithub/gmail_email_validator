package com.fetchrewards.gmail.controller;

import com.fetchrewards.gmail.controller.service.EmailAddressService;
import com.fetchrewards.gmail.entity.EmailAddresses;
import com.fetchrewards.gmail.entity.UniqCount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/email")
public class EmailAddressController {

    @Autowired
    private EmailAddressService emailAddressService;

    @GetMapping("/uniqcount")
    @PostMapping(value = "/uniqcount",
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    // We can add swagger documentation as needed.
    public UniqCount getUniqAddressCountWithGET(@RequestParam("addresses") List<String> addresses) {
        return new UniqCount(emailAddressService.countUniqueEmailAddresses(addresses));
    }

    @PostMapping(value = "/uniqcount",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    // We can add swagger documentation as needed.
    public UniqCount getUniqAddressCountWithPOST(@RequestBody EmailAddresses email) {
        return new UniqCount(emailAddressService.countUniqueEmailAddresses(email.getAddresses()));
    }
}
