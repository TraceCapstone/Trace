package com.trace.trace.controllers;

import com.trace.trace.models.Application;
import com.trace.trace.models.User;
import com.trace.trace.repositories.ApplicationRepository;
import com.trace.trace.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationController {

    private final ApplicationRepository applicationDao;
    private final UserService userService;

    public ApplicationController(ApplicationRepository applicationDao, UserService userService) {
        this.applicationDao = applicationDao;
        this.userService = userService;
    }

    //VIEW APPLICATION SUBMISSION FORM
    @GetMapping("/create-application")
    public String viewCreateApplicationForm(Model model) {
        model.addAttribute("application", new Application());
        return "create-application";
    }

    //CANCEL FORM SUBMISSION
    @GetMapping("/create-application")
    public String cancelFormSubmit() {
        //INPUT IS BUTTON CLICK

        return "ALL APPLICATIONS VIEW";
    }

    //SAVE APPLICATION FORM
//    @PostMapping("/create-application")
//    public String saveApplication() {
//        User user = userService.loggedInUser();
//
//
//    }

    //EDIT APPLICATION

    //DELETE APPLICATION
}
