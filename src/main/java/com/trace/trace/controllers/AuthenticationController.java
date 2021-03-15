package com.trace.trace.controllers;

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
        userDao.save(user);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profileView(){
        return "profile";
    }

    @GetMapping("/user/{id}")
    public String viewUserUpdateForm(Model model, @PathVariable String id) {
        model.addAttribute("user", new User().getId());
        return "profile";
    }

    @PostMapping("/user/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user, Model model) {
        User currentUser = userService.loggedInUser();
        model.addAttribute("user", currentUser);

        userDao.save(user);
        return "redirect:/profile";
    }


    @PostMapping("/posts/{id}/delete")
    public String deleteUser(@PathVariable long id){
        System.out.println("Deleting User...");
        userDao.deleteById(id);
        return "redirect:/";
    }


}
