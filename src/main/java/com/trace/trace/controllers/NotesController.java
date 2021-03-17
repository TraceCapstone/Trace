package com.trace.trace.controllers;

import com.trace.trace.models.Application;
import com.trace.trace.models.Note;
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

    @PostMapping("/notes")
    public String CrudNotes(@ModelAttribute Note note,  @RequestParam(name = "application") String applicationId){

        Application app = applicationDao.getOne(Long.parseLong(applicationId));
        note.setApplications(app);
        note.setCreatedAt((new Date(System.currentTimeMillis())));
        notesDao.save(note);
        return "redirect:/applications/"+applicationId;
    }
}
