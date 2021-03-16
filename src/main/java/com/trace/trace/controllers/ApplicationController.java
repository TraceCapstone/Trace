package com.trace.trace.controllers;

import com.trace.trace.models.*;
import com.trace.trace.repositories.ApplicationRepository;
import com.trace.trace.services.UserService;
import com.trace.trace.repositories.ApplicationStageRepository;
import com.trace.trace.repositories.StageRepository;
import com.trace.trace.repositories.UserRepository;
import com.trace.trace.repositories.ResumeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationController {

    private final ApplicationRepository applicationDao;
    private ResumeRepository resumeDao;
    private final UserService userService;
    private final StageRepository stageDao;
    private final UserRepository userDao;
    private final ApplicationStageRepository applicationsStageDao;

 public ApplicationController(ApplicationRepository applicationDao, UserService userService, ResumeRepository resumeDao, StageRepository stageDao, UserRepository userDao, ApplicationStageRepository applicationsStageDao) {
        this.applicationDao = applicationDao;
        this.userService = userService;
        this.resumeDao = resumeDao;
        this.stageDao = stageDao;
        this.userDao = userDao;
        this.applicationsStageDao = applicationsStageDao;
    }



    //VIEW ALL JOBS APPLIED FOR
    @GetMapping("/applications")
    public String viewAllAppliedJobs(Model model) {
        model.addAttribute("jobApplications", applicationDao.findAll());
        return "applications";
    }

    //VIEW INDIVIDUAL JOB APPLIED FOR
    @GetMapping("/applications/{id}")
    public String viewIndividualJob(Model model, @PathVariable long id) {
        model.addAttribute("poc", new PointOfContact());
        Application application = applicationDao.getOne(id);
        model.addAttribute("jobApplication", application);
//        Stage stage = applicationDao.findMostRecentStageForApplication(id);
//        model.addAttribute("stage",stage);
//        Note note = applicationDao.findAll(id);
//        model.addAttribute("notes", note);
        return "app";
    }

    //VIEW APPLICATION SUBMISSION FORM
    @GetMapping("/create-application")
    public String viewCreateApplicationForm(Model model) {
        model.addAttribute("jobApplication", new Application());
        model.addAttribute("stage", stageDao.findAll());
        model.addAttribute("resumes", resumeDao.findAllByUserId(userService.loggedInUser().getId()));
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
    public String saveApplication(@ModelAttribute Application application, @RequestParam("resume") String resumeId, @RequestParam(name = "test") String applicationStage) {
        User user = userDao.findById(userService.loggedInUser().getId()).get();
        application.setResume(resumeDao.getOne(Long.parseLong(resumeId)));
        application.setUser(user);
        application.setDateCreated(new Date(System.currentTimeMillis()));
        Application savedApplication = applicationDao.save(application);
        Stage stage = stageDao.findById(Long.parseLong(applicationStage)).get();
        ApplicationStage appStage = new ApplicationStage(new Date(System.currentTimeMillis()), application, stage);
        appStage.setApplication(savedApplication);
        appStage.setStage(stage);
        applicationsStageDao.save(appStage);
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
