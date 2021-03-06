package com.trace.trace.controllers;

import com.trace.trace.models.*;
import com.trace.trace.repositories.*;
import com.trace.trace.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final NotesRepository noteDao;
    @Value("${api-key}")
    private String apiKey;

 public ApplicationController(ApplicationRepository applicationDao, UserService userService, ResumeRepository resumeDao, StageRepository stageDao, UserRepository userDao, ApplicationStageRepository applicationsStageDao, NotesRepository noteDao) {
        this.applicationDao = applicationDao;
        this.userService = userService;
        this.resumeDao = resumeDao;
        this.stageDao = stageDao;
        this.userDao = userDao;
        this.applicationsStageDao = applicationsStageDao;
        this.noteDao = noteDao;
 }



    //VIEW ALL JOBS APPLIED FOR
    @GetMapping("/applications")
    public String viewAllAppliedJobs(Model model) {
     User user = userDao.getOne(userService.loggedInUser().getId());
        model.addAttribute("apiKey", apiKey);
        model.addAttribute("jobApplications", applicationDao.findAllByUser(user));
        return "applications";
    }

    //VIEW INDIVIDUAL JOB APPLIED FOR
    @GetMapping("/applications/{id}")
    public String viewIndividualJob(Model model, @PathVariable long id) {
        model.addAttribute("note", new Note());
        model.addAttribute("poc", new PointOfContact());
        Application application = applicationDao.getOne(id);
        model.addAttribute("jobApplication", application);
        List<ApplicationStage> appStage = application.getApplicationStage();
        Stage stage = appStage.get(appStage.size() - 1).getStage();
        model.addAttribute("stage", stage);
        model.addAttribute("date", appStage.get(appStage.size() - 1).getCreatedAt());
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
    public String saveApplication(@ModelAttribute Application application, @RequestParam(value = "resume", required = false) String resumeId, @RequestParam(name = "test") String applicationStage) {
        User user = userDao.findById(userService.loggedInUser().getId()).get();
        if(resumeId != null)
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
    @GetMapping("/applications/edit/{id}")
    public String viewEditApplicationForm(@PathVariable long id, Model model) {
        User user = userDao.findById(userService.loggedInUser().getId()).get();
        Application application = applicationDao.getOne(id);
        List <Resume> resumes = resumeDao.findAllByUserId(user.getId());

        model.addAttribute("jobApplication", applicationDao.getOne(id));
        model.addAttribute("stage", stageDao.findAll());
        model.addAttribute("resumes", resumes);
        return "edit";
    }

    @PostMapping("/applications/edit/{id}")
    public String updateApplication(@PathVariable long id, @ModelAttribute Application application, @RequestParam(value = "resume", required = false) Long resumeId , @RequestParam(name="test") String stageId) {
        User user = userService.loggedInUser();
        Stage stage = stageDao.findById(Long.parseLong(stageId)).get();

        application.setUser(user);
        Application app = applicationDao.getOne(id);
        if(resumeId != null)
            application.setResume(resumeDao.getOne(resumeId));

        application.setDateCreated(app.getDateCreated());
        Application savedApplication = applicationDao.save(application);
        ApplicationStage appStage = new ApplicationStage(new Date(System.currentTimeMillis()), application, stage);
        appStage.setApplication(savedApplication);
        appStage.setStage(stage);

        applicationsStageDao.save(appStage);

        return "redirect:/applications";
    }

    //DELETE APPLICATION
    @PostMapping("/applications/{id}/delete")
    public String deleteApplication(@PathVariable long id){
        applicationDao.deleteById(id);
        return "redirect:/applications";
    }


}
