package com.trace.trace.controllers;

import com.trace.trace.models.*;
import com.trace.trace.repositories.ApplicationRepository;
import com.trace.trace.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class ApplicationController {

    private final ApplicationRepository applicationDao;
    private final UserService userService;

    public ApplicationController(ApplicationRepository applicationDao, UserService userService) {
        this.applicationDao = applicationDao;
        this.userService = userService;
    }



    //VIEW ALL JOBS APPLIED FOR
    @GetMapping("/applications")
    public String viewAllAppliedJobs(Model model) {
        model.addAttribute("applications", applicationDao.findAll());
        return "applications";
    }

    //VIEW INDIVIDUAL JOB APPLIED FOR
    @GetMapping("/applications/{id}")
    public String viewIndividualJob(Model model, @PathVariable long id) {
        Application application = applicationDao.getOne(id);
        model.addAttribute("jobApplication", application);
        Stage stage = applicationDao.findMostRecentStageForApplication(id);
        model.addAttribute("stage",stage);
//        Note note = applicationDao.findAll(id);
//        model.addAttribute("notes", note);
        return "app";
    }

    //VIEW APPLICATION SUBMISSION FORM
    @GetMapping("/create-application")
    public String viewCreateApplicationForm(Model model) {
        model.addAttribute("jobApplication", new Application());
        model.addAttribute("resume", new Resume());
        return "create-application";
    }

    //CANCEL FORM SUBMISSION
    @GetMapping("/create-application/cancel")
    public String cancelFormSubmit() {
        //ON BUTTON CLICK

        return "applications";
    }

    //SAVE APPLICATION FORM
    @PostMapping("/create-application")
    public String saveApplication(@ModelAttribute Application application) {
        User user = userService.loggedInUser();
        application.setUser(user);
        application.setDateCreated(new Date(System.currentTimeMillis()));
        Application savedApplication = applicationDao.save(application);
        return "redirect:/applications";
    }

    //EDIT APPLICATION
    @GetMapping("/applications/{id}/edit")
    public String viewEditApplicationForm(@PathVariable long id, Model model) {
        model.addAttribute("jobApplication", applicationDao.getOne(id));
        return "edit";
    }

    @PostMapping("/applications/{id}/edit")
    public String updateApplication(@PathVariable long id, @ModelAttribute Application application) {
        User user = userService.loggedInUser();
        application.setUser(user);
        applicationDao.save(application);
        return "redirect:/applications";
    }

    //DELETE APPLICATION
    @PostMapping("/applications/{id}/delete")
    public String deleteApplication(@PathVariable long id){
        applicationDao.deleteById(id);
        return "redirect:/applications";
    }


}
