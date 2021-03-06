package com.trace.trace.controllers;

import com.trace.trace.models.Application;
import com.trace.trace.models.PointOfContact;
import com.trace.trace.repositories.ApplicationRepository;
import com.trace.trace.repositories.PointOfContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;;

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
        String formattedPhone;
        if (!poc.getPhoneNumber().equals("")) {
            if(!poc.getPhoneNumber().contains("-")) {
                formattedPhone = poc.getPhoneNumber().substring(0, 3) + "-" + poc.getPhoneNumber().substring(3, 6) + "-" + poc.getPhoneNumber().substring(6);
                poc.setPhoneNumber(formattedPhone);
            }
        }
        poc.setApplication(app);
        pocDao.save(poc);
        return "redirect:/applications/"+applicationId;
    }

    @PostMapping("/poc/delete/{id}")
    public String deletePoc(@PathVariable long id, @RequestParam(name = "application") String applicationId) {
        pocDao.deleteById(id);
        return "redirect:/applications/"+applicationId;
    }

    @GetMapping("/poc/edit/{id}")
    public String editPoc(Model model,  @PathVariable long id) {
        PointOfContact poc = pocDao.getOne(id);
        model.addAttribute("poc", poc);
        return "poc/edit-poc";
    }

    @PostMapping("/poc/edit/{id}")
    public String editPoc(@PathVariable long id, @ModelAttribute PointOfContact poc) {
        pocDao.update(poc.getEmail(), poc.getFirstName(), poc.getLastName(), poc.getPhoneNumber(), poc.getPosition(), id);
        Application app = applicationDao.findByPoc(poc);
        return "redirect:/applications/"+app.getId();
    }


}
