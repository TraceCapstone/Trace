package com.trace.trace.controllers;

import com.trace.trace.models.Application;
import com.trace.trace.models.Note;
import com.trace.trace.models.PointOfContact;
import com.trace.trace.models.User;
import com.trace.trace.repositories.ApplicationRepository;
import com.trace.trace.repositories.NotesRepository;
import com.trace.trace.repositories.UserRepository;
import com.trace.trace.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class NotesController {

    private final NotesRepository notesDao;
    private final ApplicationRepository applicationDao;
    private final UserService userService;

    public NotesController(NotesRepository notesDao, ApplicationRepository applicationDao, UserService userService){
        this.notesDao = notesDao;
        this.applicationDao = applicationDao;
        this.userService = userService;

    }
    @GetMapping("/note/{id}")
    public String viewIndividualNote(Model model, @PathVariable long id) {
        Note note = notesDao.getOne(id);
        model.addAttribute("notes", note);
        return "/notes";
    }

    @PostMapping("/note/{id}")
    public String updateNote(@PathVariable long id, @ModelAttribute Note note){
//        notesDao.update(note.getBody(), id);
        return "redirect:/applications";
    }

    @PostMapping("/notes")
    public String CreatNotes(@ModelAttribute Note note,  @RequestParam(name = "application") String applicationId){

        Application app = applicationDao.getOne(Long.parseLong(applicationId));
        note.setApplications(app);
        note.setCreatedAt((new Date(System.currentTimeMillis())));
        notesDao.save(note);
        return "redirect:/applications/"+applicationId;
    }

    @PostMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable long id, @RequestParam(name = "application") String applicationId){
        notesDao.deleteById(id);
        return "redirect:/applications"+applicationId;
    }
}
