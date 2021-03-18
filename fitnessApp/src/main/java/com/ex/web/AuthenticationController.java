package com.ex.web;

import com.ex.models.calendar.CalendarDay;
import com.ex.models.users.User;
import com.ex.models.users.UserLoginDTO;
import com.ex.services.CalendarService;
import com.ex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin
@RestController
@RequestMapping("/authentication")

public class AuthenticationController {
        UserService userService;

        @Autowired
        public void setUserService(UserService userService){
            this.userService = userService;
        }
    @CrossOrigin
        @PostMapping(path="register",
                produces = MediaType.APPLICATION_JSON_VALUE)
        public String register(@RequestBody User user){
            int authValue = userService.save(user);
            if(authValue == 1){
                return "/home";
            }
            return "/register";
        }

    @CrossOrigin
        @PostMapping(path="login",
                produces={MediaType.APPLICATION_JSON_VALUE})
            public String authenticate(@RequestBody UserLoginDTO user) {
            System.out.println(user.getUserName() + " " + user.getPassword());
        System.out.println("ALKJDSFONIAEBNAFONEOINFWOIENFOWIENF");
            return userService.authenticate(user.getUserName(), user.getPassword());
        }

    @CrossOrigin
    @PostMapping(path="getUser",
            produces={MediaType.APPLICATION_JSON_VALUE})
        public User getUser(@RequestBody UserLoginDTO user) {
        return userService.getByUsername(user.getUserName());
    }

}
