package com.ex.web;

import com.ex.models.entities.StatTrackingObj;
import com.ex.models.users.User;
import com.ex.services.StatService;
import com.ex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/stats")
public class StatTrackingController {
    StatService service;
    UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setService(StatService service){
        this.service = service;
    }

    @CrossOrigin
    @PostMapping(path="persist",
            produces={MediaType.APPLICATION_JSON_VALUE})
    public String saveStats(StatTrackingObj stats) {
            service.save(stats);
        return "/stats";
    }
    @CrossOrigin
    @PostMapping(path="getStats",
            produces={MediaType.APPLICATION_JSON_VALUE})
    public StatTrackingObj getStats(String userName) {
        System.out.println(userName);
        User user = userService.getByUsername(userName);
        return service.getByUserId(user.getUserId());
    }
}
