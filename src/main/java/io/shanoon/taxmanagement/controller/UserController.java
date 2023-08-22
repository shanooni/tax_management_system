package io.shanoon.taxmanagement.controller;

import io.shanoon.taxmanagement.model.User;
import io.shanoon.taxmanagement.service.UserCustomService;
import io.shanoon.taxmanagement.service.UserService;
import io.shanoon.taxmanagement.utility.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserCustomService userCustomService;
    private final UserService userService;

    public UserController(UserCustomService userCustomService, UserService userService) {
        this.userCustomService = userCustomService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO user){
        userService.registerUser(user);
        return new ResponseEntity<>("Registered Successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(UserDTO user){
        userService.loginUser(user);
        return new ResponseEntity<>("login successfully", HttpStatus.ACCEPTED);
    }
    @GetMapping("/users")
    public ResponseEntity<Collection<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email){
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }
}
