package com.ex.web;

import com.ex.models.calendar.CalendarDay;
import com.ex.models.users.UserLoginDTO;
import com.ex.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin
@RestController
@RequestMapping("/calendar")

public class CalendarController {
    CalendarService calendarService;

    @Autowired
    public void setCalendarService(CalendarService calendarService){
        this.calendarService = calendarService;
    }

    @CrossOrigin
    @PostMapping(path="getCalendar",
            produces={MediaType.APPLICATION_JSON_VALUE})
        public ArrayList<CalendarDay> buildCalendar(@RequestBody UserLoginDTO user) {
        System.out.println(user.getUserName());
        return calendarService.buildCalendar(user.getUserName());
    }
}
