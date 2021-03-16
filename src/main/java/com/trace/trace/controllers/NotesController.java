package com.trace.trace.controllers;

import com.trace.trace.models.Note;
import com.trace.trace.models.User;
import com.trace.trace.repositories.NotesRepository;
import com.trace.trace.repositories.UserRepository;
import com.trace.trace.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotesController {

    private final NotesRepository notesDao;
    private final UserRepository userDao;
    private final UserService userService;

    public NotesController(NotesRepository notesDao, UserRepository userDao, UserService userService){
        this.notesDao = notesDao;
        this.userDao = userDao;
        this.userService = userService;
    }

    @GetMapping("/notes")
    public String showsNotesForm(Model model){
        model.addAttribute("note", new Note());
        return "notes";
    }

    @PostMapping("/notes")
    public String CrudNotes(@ModelAttribute Note note){

        User user = userService.loggedInUser();
        note.setUser(user);
        notesDao.save(note);
        return "redirect:/notes";
    }
}
