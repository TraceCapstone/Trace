package com.trace.trace.contollers;

import com.trace.trace.models.Application;
import com.trace.trace.repositories.ApplicationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    private final ApplicationRepository applicationDao;

    public ApplicationController(ApplicationRepository applicationDao) {
        this.applicationDao = applicationDao;
    }

    //VIEW APPLICATION SUBMISSION FORM
    @GetMapping("/create-application")
    public String viewCreateApplicationForm(Model model) {
        model.addAttribute("application", new Application());
        return "create-application";
    }

    //CANCEL FORM SUBMISSION
    @GetMapping
    public String cancelFormSubmit() {

    }

    //SAVE APPLICATION FORM

    //EDIT APPLICATION

    //DELETE APPLICATION
}
