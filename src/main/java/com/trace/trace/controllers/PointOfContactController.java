package com.trace.trace.controllers;

import com.trace.trace.models.Application;
import com.trace.trace.models.PointOfContact;
import com.trace.trace.repositories.ApplicationRepository;
import com.trace.trace.repositories.PointOfContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PointOfContactController {

    private final PointOfContactRepository pocDao;
    private final ApplicationRepository applicationDao;

    public PointOfContactController(PointOfContactRepository pocDao, ApplicationRepository applicationDao) {
        this.pocDao = pocDao;
        this.applicationDao = applicationDao;
    }

    @PostMapping("/poc")
    public String addPoc(@ModelAttribute PointOfContact poc, @RequestParam(name = "application") String applicationId) {
        Application app = applicationDao.getOne(Long.parseLong(applicationId));
        poc.setApplication(app);
        pocDao.save(poc);
        return "redirect:/applications/"+applicationId;
    }

}
