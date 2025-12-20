package com.springboot.callDownloader.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.callDownloader.ctService.ctService;

@RestController
@RequestMapping("/cloudtalk")
public class Controller {

    @Autowired
    private ctService ctService; 

    @GetMapping("/calls")
    public String ctCalls() {
        return ctService.getCalls();
    }
    
    @GetMapping("/agents")
    public String ctAgents() {
        return ctService.getAgents();
    }

    @GetMapping("/numbers")
    public String ctNumbers() {
        return ctService.getNumbers();
    }
}