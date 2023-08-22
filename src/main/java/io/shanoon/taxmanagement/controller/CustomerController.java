package io.shanoon.taxmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {
    @GetMapping(value = "/api/v1/customer/hello",produces = "application/json")
    String greet(){
        return "Hello and welcome";
    }
}
