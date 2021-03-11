package com.trace.trace.controllers;

import com.trace.trace.models.User;
import com.trace.trace.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class authenticationController {


    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
//    private final UserDetailsLoader userDetailsLoader;
//    private final UserService userService;

    public authenticationController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;

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
    public String profileView(Model model){
//        User currUser = UserService.loggedInUser();
//        model.addAttribute("user", currUser);
        return "profile";
    }


}
