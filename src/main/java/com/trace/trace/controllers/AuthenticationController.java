package com.trace.trace.controllers;

import com.trace.trace.models.Resume;
import com.trace.trace.models.User;
import com.trace.trace.repositories.UserRepository;
import com.trace.trace.services.UserDetailsLoader;
import com.trace.trace.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthenticationController {


    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthenticationController(UserRepository userDao, PasswordEncoder passwordEncoder, UserService userService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/sign-up")
    public String showsSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@Valid @ModelAttribute User user, Errors e, Model model) {
        if (e.hasErrors()) {
            model.addAttribute("errors", e);
            return "sign-up";
        } else if (userDao.findByUsername(user.getUsername()) != null) {
            model.addAttribute("username", user.getUsername());
            return "sign-up";
        } else if (userDao.findByEmail(user.getEmail()) != null) {
            model.addAttribute("email", user.getEmail());
            return "sign-up";
        }

        String password = user.getPassword();
        String hash = passwordEncoder.encode(password);
        user.setPassword(hash);
        String formattedFirstName = user.getFirstName().substring(0, 1).toUpperCase() + user.getFirstName().substring(1);
        String formattedLastName = user.getLastName().substring(0, 1).toUpperCase() + user.getLastName().substring(1);
        user.setFirstName(formattedFirstName);
        user.setLastName(formattedLastName);
        userDao.save(user);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profileView(Model model){
        User freshUser = userDao.findById(userService.loggedInUser().getId()).get();
        model.addAttribute("user", freshUser);
        model.addAttribute("resume", new Resume());
        return "profile";
    }


    @PostMapping("/profile")
    public String updateUser(@ModelAttribute("user") User user, Model model, @ModelAttribute("userDeleted") User userDeleted) {
//        User user1 = userDao.findById(user.getId()).get();
//        userDao.save(user1);
        User updatedUser = userDao.findById(user.getId()).get();
        updatedUser.setEmail(user.getEmail());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        userDao.save(updatedUser);
//        userDao.delete(userDeleted);
        return "redirect:/profile";
    }

}
